package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import util.Decomposer;

import compile.AttInfo;
import compile.CountedAttInfo;
import compile.Expression;
import compile.CountedTableData;
import compile.TableData;
import execute.AggFunc;
import execute.Attribute;
import execute.Grouping;
import execute.Join;
import execute.Selection;

public class Worker {

	/**
	 * Create an instance of Checker
	 * @param database 
	 * @param group 
	 * @param where 
	 * @param myFrom 
	 * @param mySelect 
	 */
	public Worker()
	{
		
	}

	private ArrayList <Attribute> getAttrFromTable(TableData td)
	{
		Attribute[] in=new Attribute[td.getAttrCount()*2];
		Iterator<AttInfo> iter=td.iterator();
		while(iter.hasNext())
		{
			AttInfo att=iter.next();
			String name=att.getName();
			if(name.substring(name.length()-3, name.length()).equals("out"))
			{
				name=name.substring(0,name.length()-3);
			}
			else if(name.length()>=8)
			{
				if(name.substring(name.length()-7, name.length()).equals("outLeft"))
				{
					name=name.substring(0,name.length()-7);
				}
				else if(name.substring(name.length()-8, name.length()).equals("outRight"))
				{
					name=name.substring(0,name.length()-8);
				}
			}
			
			//TODO because this index out of bound, thus, twice the size of the array,
			//need to find the true bug
			in[att.getAttSequenceNumber()]=new Attribute(att.getDataType(),name);
		}

		ArrayList <Attribute> inAtts = new ArrayList <Attribute> ();
		for(int i=0;i<in.length;i++)
		{
			if(in[i]!=null)
			inAtts.add(in[i]);
		}

		return inAtts;
	}


	public TableData selection (String outputName, TableData inputTable, 
			OutputStructure outputStructure, String selectionStatement) 
	{
		System.out.println ("Start running a selection on "+outputName);

		//get the input parameters for selection
		ArrayList <Attribute> inAtts = getAttrFromTable(inputTable);
		ArrayList <Attribute> outAtts = outputStructure.getOutAtts();
		String selection = selectionStatement;
		HashMap <String, String> exprs = outputStructure.getExprs();

		//save the information for the output table
		int outIndex=0;
		Map <String, AttInfo> outputAttrs=new HashMap<String, AttInfo>();
		Iterator<Attribute> outAttsIter=outAtts.iterator();
		while(outAttsIter.hasNext())
		{
			Attribute a=outAttsIter.next();
			String name=a.getName();
			if(name.substring(name.length()-3, name.length()).equals("out"))
			{
				name=name.substring(0,name.length()-3);
			}
			//int i=Integer.parseInt(n.substring(n.length()-1, n.length()));
			AttInfo ai=new AttInfo(name,a.getType(),outIndex++);
			outputAttrs.put(name, ai);
		}

		TableData output=new TableData(outputName, outputAttrs);

		// run the selection operation
		try {
			
			System.out.println("inAtts:\n");
			print(inAtts);
			System.out.println("outAtts:\n");
			print(outAtts);
			System.out.println("selection:\n");
			System.out.println(selection+"\n");
			System.out.println("exprs:\n");
			Set<String> exp=exprs.keySet();
			Iterator<String> itera=exp.iterator();
			while(itera.hasNext())
			{
				String ss=itera.next();
				System.out.println(ss+" "+exprs.get(ss)+"\n");
			}
			System.out.println("file:\n");
			System.out.println(inputTable.getName()+".tbl\n");
			 
			Selection foo = new Selection (inAtts, outAtts, selection, exprs, inputTable.getName()
					+".tbl", outputName+".tbl", "g++", "cppDir/");

			System.out.println(output.print());

			return output;
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	public TableData join (String outputName,TableData leftTable, TableData rightTable, 
			ArrayList <String> leftHash, ArrayList <String> rightHash,
			OutputStructure outputStructure, String selectionStatement) 
	{
		System.out.println ("Start running a join on "+outputName);

		ArrayList <Attribute> inAttsLeft = getAttrFromTable(leftTable);
		ArrayList <Attribute> inAttsRight = getAttrFromTable(rightTable);
		ArrayList <Attribute> outAtts = outputStructure.getOutAtts();

		String selection = selectionStatement;
		HashMap <String, String> exprs = outputStructure.getExprs(); 

		//save the information for the output table
		int outIndex=0;
		Map <String, AttInfo> outputAttrs=new HashMap<String, AttInfo>();
		Iterator<Attribute> outAttsIter=outAtts.iterator();
		while(outAttsIter.hasNext())
		{
			Attribute a=outAttsIter.next();
			String name=a.getName();
			if(name.substring(name.length()-3, name.length()).equals("out"))
			{
				name=name.substring(0,name.length()-3);
			}
			else if(name.length()>=8)
			{
				if(name.substring(name.length()-7, name.length()).equals("outLeft"))
				{
					name=name.substring(0,name.length()-7);
				}
				else if(name.substring(name.length()-8, name.length()).equals("outRight"))
				{
					name=name.substring(0,name.length()-8);
				}
			}
			AttInfo ai=new AttInfo(name,a.getType(),outIndex++);
			outputAttrs.put(name, ai);
		}

		TableData output=new TableData(outputName, outputAttrs);

		// run the join
		try {
			
			System.out.println("inAttsLeft:\n");
			print(inAttsLeft);
			System.out.println("inAttsRight:\n");
			print(inAttsRight);
			Iterator<String> iterl=leftHash.iterator();
			while(iterl.hasNext())
			{
				System.out.println("leftHash: "+iterl.next());
			}

			Iterator<String> iterr=rightHash.iterator();
			while(iterr.hasNext())
			{
				System.out.println("rightHash: "+iterr.next());
			}
			System.out.println("outAtts:\n");
			print(outAtts);
			System.out.println("selection:\n");
			System.out.println(selection+"\n");
			System.out.println("exprs:\n");
			Set<String> exp=exprs.keySet();
			Iterator<String> itera=exp.iterator();
			while(itera.hasNext())
			{
				String ss=itera.next();
				System.out.println(ss+" "+exprs.get(ss)+"\n");
			}
			System.out.println("Files: lef="+leftTable.getName()+".tbl right="+rightTable.getName()+".tbl");
			

			Join foo = new Join (inAttsLeft, inAttsRight, outAtts, leftHash, rightHash, selection, 
					exprs, leftTable.getName()+".tbl", rightTable.getName()+".tbl", outputName+".tbl",
					"g++", "cppDir/"); 

			System.out.println(output.print());
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException (e);
		}

	}

	public void groupby (String outputName,TableData inputTable,OutputStructure outputStructure, 
			ArrayList <String> groupingAtts) 
	{
		System.out.println ("Start running a groupby on "+outputName);

		ArrayList <Attribute> inAtts = getAttrFromTable(inputTable);
		ArrayList <Attribute> outAtts = outputStructure.getOutAtts();
		HashMap <String, AggFunc> myAggs = outputStructure.getFuncs();
		
		System.out.println("Input Files: "+inputTable.getName()+".tbl");

		// run the selection operation
		try {
			Grouping foo = new Grouping (inAtts, outAtts, groupingAtts, myAggs, 
					inputTable.getName()+".tbl", outputName+".tbl", "g++", "cppDir/"); 
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	private void print(ArrayList <Attribute> a)
	{
		Iterator<Attribute> iter=a.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next().toString()+"\n");
		}
	}
}
