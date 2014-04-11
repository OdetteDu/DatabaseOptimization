package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import util.Decomposer;

import compile.AttInfo;
import compile.Expression;
import compile.CountedTableData;
import compile.TableData;
import execute.AggFunc;
import execute.Attribute;

public class Analyzer {

	private Decomposer decomposer;
	private Map<String, CountedTableData> database;
	private ArrayList<Expression> select;
	private Map<String, String> from; 
	private Expression where;
	private String group;

	private Worker worker;

	private boolean needGroup;

	/**
	 * Stores all the tables this query is referring to
	 * String tableName
	 */
	private HashMap<String,CountedTableData> primarytables;

	/**
	 * Stores all the selections to do before join
	 * String o
	 */
	private HashMap<String, SelectionsTodo> singleTableSelections;

	/**
	 * Stores all the join to do
	 * String o
	 */
	private HashMap<MultiId, JoinsTodo> multiTableJoins;

	/**
	 * Match identifier o and the required Attributes o_orderkey
	 */
	private HashMap<String, ArrayList<String>>requiredAttributes;
	
	private ArrayList<String> idsTobeJoin;

	/**
	 * Create an instance of Checker
	 * @param database 
	 * @param group 
	 * @param where 
	 * @param myFrom 
	 * @param mySelect 
	 */
	public Analyzer(Map<String, CountedTableData> database, ArrayList<Expression> select, Map<String,
			String> from, Expression where, String group)
	{
		needGroup=false;
		decomposer=new Decomposer(database,from);
		this.database=database;
		this.select=select;
		this.from=from;
		this.where=where;
		this.group=group;
		worker=new Worker();
		singleTableSelections=new HashMap<String, SelectionsTodo>();
		multiTableJoins=new HashMap<MultiId, JoinsTodo>();
		requiredAttributes=new HashMap<String, ArrayList<String>>();
		idsTobeJoin=new ArrayList<String>();
	}

	public void analyze()
	{
		primarytables=analyzeFrom();
		OutputStructure finalOutput=analyzeSelect();
		ArrayList<String> groupbyAttr=analyzeGroup();
		analyzeWhere();

		HashMap<String,TableData> result=doSingleTableSelection();

		if(multiTableJoins.size()==0)
		{
			Iterator<String> it=from.keySet().iterator();
			String id=it.next();

			if(!needGroup)
			{
				//normal selection
				worker.selection ("out", result.get(id), 
						finalOutput, "1==1");
			}
			else
			{
				//group selection
				worker.groupby("out", result.get(id), finalOutput, groupbyAttr);
			}
		}
		else
		{
			//sortTobeJoin();
			
			TableData td=join(result);
			
			if(!needGroup)
			{
				//normal selection
				worker.selection ("out", td, finalOutput, "1==1");
			}
			else
			{
				//group selection
				worker.groupby("out", td, finalOutput, groupbyAttr);
			}
			
		}
	}



	private HashMap<String,CountedTableData> analyzeFrom()
	{
		HashMap<String,CountedTableData> fromTable=new HashMap<String,CountedTableData>();

		Set<String> tables=database.keySet();

		for(String id: from.keySet())
		{
			String tableName=from.get(id);

			//check if tableName is in the database
			if(tables.contains(tableName))
			{
				fromTable.put(tableName,database.get(tableName));
			}
		}
		return fromTable;
	}

	private OutputStructure analyzeSelect()
	{
		Iterator<Expression> iter=select.iterator();
		int index=0;
		ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
		HashMap <String, String> exprs = new HashMap<String, String>();
		HashMap <String, AggFunc> funcs = new HashMap <String, AggFunc> ();
		while(iter.hasNext())
		{
			Expression e=iter.next();
			decomposer.getAttributes(e, requiredAttributes);
			String type = decomposer.getType(e);
			String expValue = decomposer.getValue(e);
			if(expValue.contains("sum")||expValue.contains("avg"))
			{
				needGroup=true;
			}
			AggFunc aggValue = decomposer.getAggFunc(e);

			String att="att"+index;

			outAtts.add (new Attribute (type, att));
			exprs.put(att, expValue);
			funcs.put (att, aggValue);

			index++;
		}

		return new OutputStructure(outAtts,exprs,funcs);

	}

	private ArrayList<String> analyzeGroup()
	{
		ArrayList<String> groupAtts=new ArrayList<String>();
		if(group!=null)
		{
			groupAtts.add(group.substring(group.indexOf('.')+1,group.length()));

			String t=group.substring(0, group.indexOf('.'));
			if(requiredAttributes.containsKey(t))
			{
				if(!requiredAttributes.get(t).contains(group.substring(group.indexOf('.')+1,group.length())))
					requiredAttributes.get(t).add(group.substring(group.indexOf('.')+1,group.length()));
			}
			else
			{
				ArrayList<String> a=new ArrayList<String>();
				a.add(group.substring(group.indexOf('.')+1,group.length()));
				requiredAttributes.put(t, a);
			}
		}

		return groupAtts;
	}

	private void analyzeWhere()
	{
		if(where==null)
			return;
		ArrayList<Expression> exprs=new ArrayList<Expression>();
		decomposer.andBreaker(where, exprs);
		
		System.out.println("***************************************************************************");

		//print the broken expression out
		Iterator<Expression> iter=exprs.iterator();
		while(iter.hasNext())
		{
			Expression expr=iter.next();
			
			System.out.println(decomposer.getValue(expr));

			ArrayList<String> identifiers=new ArrayList<String>();
			getIdentifier(expr, identifiers);
			if(identifiers.size()<=1)
			{
				//related to single table
				String id=identifiers.get(0);

				if(!singleTableSelections.containsKey(id))
				{
					SelectionsTodo st=new SelectionsTodo(id);
					st.addExpression(expr);
					singleTableSelections.put(id, st);
				}
				else
				{
					SelectionsTodo st=singleTableSelections.get(id);
					st.addExpression(expr);
				}

			}
			else
			{
				decomposer.getAttributes(expr, requiredAttributes);
				//related to multiple tables
				String id1=identifiers.get(0);
				String id2=identifiers.get(1);
				
				//add to the idsTobeJoin table
				if(!idsTobeJoin.contains(id1))
				{
					idsTobeJoin.add(id1);
				}
				
				if(!idsTobeJoin.contains(id2))
				{
					idsTobeJoin.add(id2);
				}
				
				MultiId mid=new MultiId(id1,id2);
				if(multiTableJoins.containsKey(mid))
				{
					JoinsTodo jt=multiTableJoins.get(mid);
					jt.addExpression(expr);
				}
				else
				{
					JoinsTodo jt=new JoinsTodo(mid);
					jt.addExpression(expr);
					multiTableJoins.put(mid, jt);
				}
			}
		}


	}

	private void getIdentifier(Expression e, ArrayList<String> identifiers)
	{
		if(e.getType().equals("identifier"))
		{
			String value=e.getValue();
			value=value.substring(0, value.indexOf('.'));
			if(!identifiers.contains(value))
				identifiers.add(value);
		}
		else if(decomposer.whichUnary(e.getType())!=null)
		{
			getIdentifier(e.getSubexpression(), identifiers);
		}
		else if(decomposer.whichBinary(e.getType())!=null)
		{
			getIdentifier(e.getSubexpression("left"), identifiers);
			getIdentifier(e.getSubexpression("right"), identifiers);
		}
		else
		{

		}
	}

	private OutputStructure getOutputFromTable(String id)
	{
		ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
		HashMap <String, String> exprs = new HashMap <String, String> ();

		
		Iterator<String> iter=requiredAttributes.get(id).iterator();
		while(iter.hasNext())
		{
			String name=iter.next();

			AttInfo att=primarytables.get(from.get(id)).getAttInfo(name);
			String newName=name+"out";

			String type=att.getDataType();

			outAtts.add (new Attribute (type, newName));
			exprs.put (newName, name);
		}
		
		/*
		Iterator<AttInfo> iter=primarytables.get(from.get(id)).iterator();
		while(iter.hasNext())
		{
			AttInfo att=iter.next();
			String name=att.getName();
			String newName=name+"out";
			String type=att.getDataType();
			outAtts.add (new Attribute (type, newName));
			exprs.put (newName, name);	
		}
		*/

		return new OutputStructure(outAtts,exprs,null);
	}

	private OutputStructure getOutputFromTable(TableData left, String leftId, boolean leftIsPrimary,
			TableData right,String rightId, boolean rightIsPrimary)
	{
		ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
		HashMap <String, String> exprs = new HashMap <String, String> ();

		if(!leftIsPrimary)
		{
			Iterator<AttInfo> iter=left.iterator();
			while(iter.hasNext())
			{
				AttInfo att=iter.next();

				String name=att.getName();
				String newName=name+"outLeft";
				String type=att.getDataType();

				outAtts.add (new Attribute (type, newName));
				exprs.put (newName, "left."+name);
			}
		}
		else
		{
			
			Iterator<String> iter=requiredAttributes.get(leftId).iterator();
			while(iter.hasNext())
			{
				String name=iter.next();

				AttInfo att=primarytables.get(from.get(leftId)).getAttInfo(name);
				String newName=name+"outLeft";

				String type=att.getDataType();

				outAtts.add (new Attribute (type, newName));
				exprs.put (newName, "left."+name);
			}
			/*
			Iterator<AttInfo> iter=primarytables.get(from.get(leftId)).iterator();
			while(iter.hasNext())
			{
				AttInfo att=iter.next();
				String name=att.getName();
				String newName=name+"outLeft";
				String type=att.getDataType();
				outAtts.add (new Attribute (type, newName));
				exprs.put (newName, "left."+name);	
			}
			*/
		}

		if(!rightIsPrimary)
		{
			Iterator<AttInfo> iter2=right.iterator();
			while(iter2.hasNext())
			{
				AttInfo att=iter2.next();

				String name=att.getName();
				String newName=name+"outRight";
				String type=att.getDataType();

				outAtts.add (new Attribute (type, newName));
				exprs.put (newName, "right."+name);
			}
		}
		else
		{
			
			Iterator<String> iter=requiredAttributes.get(rightId).iterator();
			while(iter.hasNext())
			{
				String name=iter.next();

				AttInfo att=primarytables.get(from.get(rightId)).getAttInfo(name);
				String newName=name+"outRight";

				String type=att.getDataType();

				outAtts.add (new Attribute (type, newName));
				exprs.put (newName, "right."+name);
			}
			/*
			Iterator<AttInfo> iter=primarytables.get(from.get(rightId)).iterator();
			while(iter.hasNext())
			{
				AttInfo att=iter.next();
				String name=att.getName();
				String newName=name+"outRight";
				String type=att.getDataType();
				outAtts.add (new Attribute (type, newName));
				exprs.put (newName, "right."+name);	
			}
			*/
		}

		return new OutputStructure(outAtts,exprs,null);
	}

	private HashMap<String,TableData> doSingleTableSelection()
	{
		HashMap<String,TableData> tds=new HashMap<String,TableData>();

		for(String s: singleTableSelections.keySet())
		{
			SelectionsTodo st=singleTableSelections.get(s);
			TableData table=primarytables.get(from.get(s));
			String expr="";

			Iterator<Expression> iter=st.getExpression().iterator();
			while(iter.hasNext())
			{
				expr+="("+decomposer.getValue(iter.next())+")"+" && ";
			}
			expr=expr.substring(0, expr.length()-4);
			tds.put(s,worker.selection(s+"out", table, getOutputFromTable(s), expr));
		}

		return tds;
	}

	private HashMap<MultiId, TableData> doMultiTableJoin(HashMap<String, TableData> result) 
	{

		HashMap<MultiId, TableData> output= new HashMap<MultiId,TableData>();

		Iterator<MultiId> iter=multiTableJoins.keySet().iterator();
		while (iter.hasNext())
		{
			JoinsTodo jtd=multiTableJoins.get(iter.next());
			String leftId=jtd.getID().getId1();
			String rightId=jtd.getID().getId2();
			boolean leftIsPrimary;
			boolean rightIsPrimary;
			TableData tdLeft;
			TableData tdRight;
			if(result.containsKey(leftId))
			{
				tdLeft=result.get(leftId);
				leftIsPrimary=false;
			}
			else
			{
				tdLeft=primarytables.get(from.get(leftId));
				leftIsPrimary=true;
			}

			if(result.containsKey(rightId))
			{
				tdRight=result.get(rightId);
				rightIsPrimary=false;
			}
			else
			{
				tdRight=primarytables.get(from.get(rightId));
				rightIsPrimary=true;
			}

			/*
			ArrayList <String> leftHash=new ArrayList <String>();
			ArrayList <String> rightHash=new ArrayList <String>();

			Iterator<Expression> itera=jtd.getExpression().iterator();
			String expr="";
			while(itera.hasNext())
			{
				String temp=decomposer.getValue(itera.next(),leftId,rightId,leftHash,rightHash);
				//System.out.println(temp);
				expr+=temp+" && ";
			}
			expr=expr.substring(0, expr.length()-4);

			OutputStructure out=getOutputFromTable(tdLeft,leftId,leftIsPrimary, tdRight,rightId, 
					rightIsPrimary);

			TableData td=worker.join(leftId+rightId,  tdLeft, tdRight, leftHash, rightHash,
					out, expr);
					*/
			TableData td=doJoin(leftId, tdLeft, leftIsPrimary, rightId, tdRight, rightIsPrimary, 
								jtd.getExpression());
			output.put(jtd.getID(), td);
		}

		return output;
	}

	private TableData doJoin(String leftId, TableData tdLeft, boolean leftIsPrimary, 
			String rightId, TableData tdRight, boolean rightIsPrimary, ArrayList<Expression> exprs) 
	{
		ArrayList <String> leftHash=new ArrayList <String>();
		ArrayList <String> rightHash=new ArrayList <String>();

		Iterator<Expression> itera=exprs.iterator();
		String expr="";
		while(itera.hasNext())
		{
			String temp=decomposer.getValue(itera.next(),leftId,rightId,leftHash,rightHash);
			//System.out.println(temp);
			expr+=temp+" && ";
		}
		expr=expr.substring(0, expr.length()-4);

		OutputStructure out=getOutputFromTable(tdLeft,leftId,leftIsPrimary, tdRight,rightId, 
				rightIsPrimary);

		TableData td=worker.join(leftId+rightId,  tdLeft, tdRight, leftHash, rightHash,
				out, expr);
		
		return td;

	}	
	
	private TableData join(HashMap<String, TableData> result)
	{
		//Queue<String> todo=new LinkedList<String>();
		//Queue<String> temp=new LinkedList<String>();
		
		String leftId=idsTobeJoin.remove(idsTobeJoin.size()-1);
		int index=idsTobeJoin.size()-1;
		String rightId;
		ArrayList<String> finishedId=new ArrayList<String>();
		finishedId.add(leftId);
		
		//left Table Data: 
		TableData tdLeft;//get it from result or primary table depending
		//left is primary
		boolean leftIsPrimary;
		
		if(result.containsKey(leftId))
		{
			tdLeft=result.get(leftId);
			leftIsPrimary=false;
		}
		else
		{
			tdLeft=primarytables.get(from.get(leftId));
			leftIsPrimary=true;
		}
		
		
		while( !idsTobeJoin.isEmpty())
		{
			rightId=idsTobeJoin.remove(index);
			
			//System.out.println("id "+rightId+" will be joined!\nFinishedId will contain: ");
			//join the two tables below:
			//Joins todo: get from multiTableJoins,need to search which one has it
			JoinsTodo jtd=findExpression(finishedId,  rightId);
			ArrayList<Expression> exprs;
			if(jtd==null)
			{
				idsTobeJoin.add(idsTobeJoin.size(), rightId);
				index--;
				continue;
			}
			else
			{
				exprs=jtd.getExpression();
				String id1=jtd.getID().getId1();
				//leftId=(rightId==id1)? jtd.getID().getId2():id1;
				if(rightId.equals(id1))
				{
					leftId=jtd.getID().getId2();
				}
				else
				{
					leftId=jtd.getID().getId1();
				}
				//System.out.println("Find the match: LeftId="+leftId+" RightId="+rightId);
				index=idsTobeJoin.size()-1;
			}
			
			//right Table Data: 
			TableData tdRight;
			//right is primary
			boolean rightIsPrimary;
			
			if(result.containsKey(rightId))
			{
				tdRight=result.get(rightId);
				rightIsPrimary=false;
			}
			else
			{
				tdRight=primarytables.get(from.get(rightId));
				rightIsPrimary=true;
			}
			
			tdLeft=doJoin(leftId, tdLeft, leftIsPrimary, rightId, tdRight, rightIsPrimary, exprs);
			leftIsPrimary=false;

			finishedId.add(rightId);
			/*
			Iterator<String> iter=finishedId.iterator();
			while (iter.hasNext())
			{
				System.out.println(iter.next());
			}
			*/
			
		}
		return tdLeft;
	}
	
	private JoinsTodo findExpression(ArrayList<String> finishedId, String rightId)
	{
		JoinsTodo jtd=null;
		
		Iterator<String> iter=finishedId.iterator();
		while (iter.hasNext())
		{
			jtd=multiTableJoins.remove(new MultiId(iter.next(),rightId));
			if(jtd!=null)
				return jtd;
		}
		
		return jtd;
	}
	
	private void sortTobeJoin()
	{
		ArrayList<TableInfo> sortedTable=new ArrayList<TableInfo>();
		
		Iterator<String> iterIn=idsTobeJoin.iterator();
		while(iterIn.hasNext())
		{
			String s=iterIn.next();
			CountedTableData ctd=primarytables.get(from.get(s));
			int numTuples=ctd.getTupleCount();
			sortedTable.add(new TableInfo(s,numTuples));
		}
		Collections.sort(sortedTable);
		
		idsTobeJoin.clear();
		Iterator<TableInfo> iterOut=sortedTable.iterator();
		while(iterOut.hasNext())
		{
			TableInfo ti=iterOut.next();
			//System.out.println(ti.toString());
			String t=ti.getId();
			idsTobeJoin.add(t);
		}
		
		//print the list idsTobeJoin to check
		/*
		Iterator<String> iterPrint=idsTobeJoin.iterator();
		while(iterPrint.hasNext())
		{
			System.out.println(iterPrint.next());
		}
		*/
		
		
	
	}

}
