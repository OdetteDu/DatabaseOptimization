package compile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class is a checker which checks if the SQL is valid according to the SQL grammar
 * @author Caidie
 *
 */
public class Checker {

	private Map<String, CountedTableData> database;
	private ArrayList<Expression> select;
	private Map<String, String> from; 
	private Expression where;
	private String group;

	/**
	 * Create an instance of Checker
	 * @param database 
	 * @param group 
	 * @param where 
	 * @param myFrom 
	 * @param mySelect 
	 */
	public Checker(Map<String, CountedTableData> database, ArrayList<Expression> select, Map<String, String> from, 
			Expression where, String group)
	{
		this.database=database;
		this.select=select;
		this.from=from;
		this.where=where;
		this.group=group;
	}

	/**
	 * Check if the SQL is valid
	 */
	public void check() 
	{
		checkMismatch();
		checkTable();
		checkAttribute();
		checkAggregation();
	}

	/**
	 * Make sure that there are no type mismatches in any expressions. For example, it is
	 * valid to compare integers and floating point numbers, but not integers and text strings.
	 * For another example, the only arithmetic operation that is valid on a text string is a
	 * "+" (which is a concatenation)... anything else should result in an error.
	 */
	private void checkMismatch()
	{
		//check select
		Iterator<Expression> s=select.iterator();
		while(s.hasNext())
		{
			checkExpression(s.next());
		}

		//check where
		if(where!=null)
		{
			checkExpression(where);
		}
	}

	/**
	 * Make sure that all of the referenced tables exist in the database.
	 */
	private void checkTable()
	{
		Set<String> tables=database.keySet();

		for(String id: from.keySet())
		{
			String tableName=from.get(id);

			//check if tableName is in the database
			if(!tables.contains(tableName))
			{
				//not in the database
				System.out.println("Error: Table not exist! \n\t ("+tableName
						+") is not in the database!");
			}
		}
	}

	/**
	 * Make sure that all of the referenced attributes exist, and are correctly attached to the
	 * tables that are indicated in the query.
	 */
	private void checkAttribute()
	{
		ExpressionDecomposer ed=new ExpressionDecomposer();
		//check select
		Iterator<Expression> s=select.iterator();
		while(s.hasNext())
		{
			ArrayList<String> selectAttrs=ed.getIdentifiers(s.next());
			Iterator<String> selectIterator=selectAttrs.iterator();
			while(selectIterator.hasNext())
			{				
				attributesInTable(selectIterator.next(),"select clause");
			}
		}

		//check where
		if(where!=null)
		{
			ArrayList<String> whereAttrs=ed.getIdentifiers(where);

			Iterator<String> whereIterator=whereAttrs.iterator();
			while(whereIterator.hasNext())
			{			
				attributesInTable(whereIterator.next(),"where clause");
			}
		}

		//check group
		if(group!=null)
		{
			attributesInTable(group,"aggregation");
		}
	}

	/**
	 * Make sure that in the case of an aggregation query, the only selected attributes
	 * (other than the aggregates) must be functions of the grouping attributes
	 */
	private void checkAggregation()
	{
		if(group!=null)
		{
			ExpressionDecomposer ed=new ExpressionDecomposer();
			Iterator<Expression> s=select.iterator();
			while(s.hasNext())
			{
				Expression e=s.next();

				if(!e.getType().equals("sum") && !e.getType().equals("avg"))
				{
					ArrayList<String> selectAttrs=ed.getIdentifiers(e);
					Iterator<String> selectIterator=selectAttrs.iterator();

					while(selectIterator.hasNext())
					{		
						String next=selectIterator.next();
						if(!next.equals(group))
						{
							System.out.println("Error: Selected attributes not in grouping " +
									"attributes! \n\t ("+next+") is not in grouping attributes!");
						}
					}
				}
			}
		}
	}

	/**
	 * Check if the attribute in the identifier is in the database
	 * @param identifier an identifier like n.name
	 * @param type select, from, where, or aggregation
	 */
	private void attributesInTable(String identifier,String type)
	{
		String id=identifier.substring(0,identifier.indexOf("."));
		String attr=identifier.substring(identifier.indexOf(".")+1, identifier.length());
		String table=from.get(id);
		if(table!=null)
		{
			if(database.get(table)!=null)
			{
				if(database.get(table).getAttInfo(attr)==null)
				{
					System.out.println("Error: Attribute in "+type+" not exist! \n\t Table ("
							+table+") does not have the attribute ("+attr+")");
				}
			}
			else
			{
				//System.out.println("Warning: Table "+table+" not exist in the database");
			}
		}
		else
		{
			System.out.println("Error: Identifier ("+id+") not exist in the from clause");
		}
	}

	private void checkExpression(Expression e)
	{
		/*
		for (int i = 0; i < Expression.unaryTypes.length; i++) 
		{
			if (e.getType().equals (Expression.unaryTypes[i])) 
			{
				//unary
				//check if the type of the subexpression work with this operand
			}
		} 

		for (int i = 0; i < Expression.binaryTypes.length; i++) 
		{
			if (e.getType().equals (Expression.binaryTypes[i])) 
			{
				//binary
				//get type of left subexpression and right subexpression
				//check if the type of the subexpression work with this operand
			}
		} 
		 */
		getType(e);
	}

	/**
	 * get the type after execute this expression
	 * for example, 1+1 the type is integer
	 * @param e the expression to be determined
	 * @return Int, Float, or Str, or Boolean, or Error
	 */
	private String getType(Expression e)
	{
		String type=e.getType();

		if(type.equals("literal string"))
		{
			return "Str";
		}
		else if(type.equals("literal float"))
		{
			return "Float";
		}
		else if(type.equals("literal int"))
		{
			return "Int";
		}
		else if(type.equals("identifier"))
		{
			String identifier=e.getValue();
			String id=identifier.substring(0,identifier.indexOf("."));
			String attr=identifier.substring(identifier.indexOf(".")+1, identifier.length());
			String table=from.get(id);
			CountedTableData td=database.get(table);
			if(td!=null)
			{
				AttInfo a=td.getAttInfo(attr);
				if(a!=null)
				{
					return a.getDataType();
				}
				else
				{
					//System.out.println("attributes not exist");
					return "Error";
				}
			}
			else
			{
				//System.out.println("Table not exist");
				return "Error";
			}

		}
		else if(isUnary(type))
		{
			//"not", "unary minus", "sum", "avg"
			String t = getType(e.getSubexpression());
			if(t.equals("Error"))
			{
				return "Error";
			}

			if(t.equals("Int")||t.equals("Float"))
			{
				if(!e.getType().equals("not"))
				{
					return t;
				}
				else
				{
					System.out.println("Error: (not) is operating on ("+getValue(e)+") of type ("
										+t+"), not boolean!");
					return "Error";
				}
			}
			else if(t.equals("Boolean"))
			{
				if(e.getType().equals("not"))
				{
					return t;
				}
				else
				{
					System.out.println("Error: "+e.getType()+" is operating on boolean ("+getValue(e)
							+"), not numbers!");
					return "Error";
				}
			}
			else if(t.equals("Str"))
			{
				System.out.println("Error: "+e.getType()+" is operating on String ("+getValue(e)+")");
				return "Error";
			}
			else
			{
				System.out.println("Warning: invalid type");
				return "Error";
			}
		}
		else if(whichBinary(type)!=null)
		{
			//Int, Float, or Str, or Boolean
			String left=getType(e.getSubexpression("left"));
			String right=getType(e.getSubexpression("right"));

			if(left.equals("Error")||right.equals("Error"))
			{
				return "Error";
			}

			if(whichBinary(type).equals("operator"))
			{
				if(left.equals("Int")&&right.equals("Int"))
				{
					return "Int";
				}
				else if((left.equals("Int")&&right.equals("Float"))
						||(left.equals("Float")&&right.equals("Int")))
				{
					// float + int
					//System.out.println("Warning: float is "+type+" an int");
					return "Float";
				}
				else if(left.equals("Float")&&right.equals("Float"))
				{
					return "Float";
				}
				else if((left.equals("Boolean")||right.equals("Boolean")))
				{
					if((left.equals("Boolean")&&right.equals("Boolean")))
					{
						return "Boolean";
					}
					else
					{
						System.out.println("Error: invalid "+e.getType()+" on boolean type");
						return "Error";
					}
				}
				else if(left.equals("Str")||right.equals("Str"))
				{
					//only allow plus
					if(e.getType().equals("plus"))
					{
						if(left.equals("Str")&&right.equals("Str"))
						{
							return "Str";
						}
						else
						{
							System.out.println("Error: String is adding int or float!");
							return "Str";
						}
					}
					else
					{
						if(left.equals("Str")&&right.equals("Str"))
						{
							System.out.println("Error: String ("
									+getValue(e.getSubexpression("left"))+") and ("
									+getValue(e.getSubexpression("right"))
									+") is operating "+e.getType());
							return "Error";
						}
						else
						{
							if(left.equals("Str"))
							{
							System.out.println("Error: String ("+getValue(e.getSubexpression("left"))+") is operating "+e.getType()
									+" with other types of data");
							return "Error";
							}
							else
							{
								System.out.println("Error: String ("+getValue(e.getSubexpression("right"))+") is operating "+e.getType()
										+" with other types of data");
								return "Error";
							}
						}
						
					}

				}
				else
				{
					System.out.println("Error: invalid type");
					return "Error";
				}
			}
			else if(whichBinary(type).equals("logic"))
			{
				if(left.equals("Boolean")&&right.equals("Boolean"))
				{
					return "Boolean";
				}
				else
				{
					System.out.println("Error: non-boolean type is operating "+e.getType());
					return "Error";
				}
			}
			else if(whichBinary(type).equals("compare"))
			{
				if(left.equals("Int")&&right.equals("Int"))
				{
					return "Boolean";
				}
				else if(left.equals("Int")&&right.equals("Float"))
				{
					return "Boolean";
				}
				else if(left.equals("Float")&&right.equals("Int"))
				{
					return "Boolean";
				}
				else if(left.equals("Float")&&right.equals("Float"))
				{
					return "Boolean";
				}
				else if(left.equals("Str")||right.equals("Str"))
				{
					if(left.equals("Str")&&right.equals("Str"))
					{
						//System.out.println("Warning: String ("
							//	+getValue(e.getSubexpression("left"))+") and ("
							//	+getValue(e.getSubexpression("right"))
							//	+") is being compared using "+e.getType());
						return "Error";
					}
					else
					{
						//string with int
						if(left.equals("Int")||right.equals("Int"))
						{
							if(left.equals("Str"))
							{
							System.out.println("Error: String ("+getValue(e.getSubexpression("left"))
												+") is being compared using "+e.getType()
									+" with Integer");
							return "Error";
							}
							else
							{
								System.out.println("Error: String ("+getValue(e.getSubexpression("right"))
										+") is being compared using "+e.getType()+" with Integer");
								return "Error";
							}
							
						}
						//string with float
						else if(left.equals("Float")||right.equals("Float"))
						{
							if(left.equals("Str"))
							{
							System.out.println("Error: String ("+getValue(e.getSubexpression("left"))
												+") is being compared using "+e.getType()
									+" with Float");
							return "Error";
							}
							else
							{
								System.out.println("Error: String ("+getValue(e.getSubexpression("right"))
										+") is being compared using "+e.getType()+" with Float");
								return "Error";
							}
						}
						//string with other types
						else
						{
						System.out.println("Error: String is being compared using "+e.getType()
								+" with other types of data");
						return "Error";
						}
					}
					
				}
				else
				{
					System.out.println("Error: Boolean is being compared using "+e.getType());
					return "Error";
				}
			}
			else
			{
				System.out.println("Wierd type");
				return "Error";
			}
		}
		else
		{
			System.out.println("got a bad type in the expression when printing");
			return "Error";
		}
	}

	/**
	 * determine if the operator type if one of ("not", "unary minus", "sum", "avg")
	 * @param type
	 * @return true if it is a unary type
	 */
	private boolean isUnary(String type)
	{
		for (int i = 0; i < Expression.unaryTypes.length; i++) 
		{
			if (type.equals (Expression.unaryTypes[i])) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * determine which binary operator type is this type
	 * @param type
	 * @return null if it is not binary type
	 * 		   "operator" if it is one of "plus", "minus", "times","divided by"
	 *         "logic" if it is one of , "or", "and"
	 *         "compare" if it is "equals", "greater than", "less than"
	 */
	private String whichBinary(String type)
	{
		for (int i = 0; i < Expression.binaryTypes.length; i++) 
		{
			if (type.equals (Expression.binaryTypes[i])) 
			{
				if(type.equals("plus")||type.equals("minus")||type.equals("times")
						||type.equals("divided by"))
				{
					return "operator";
				}
				else if(type.equals("or")||type.equals("and"))
				{
					return "logic";
				}
				else
				{
					//"equals", "greater than", "less than"
					return "compare";
				}
			}
		}
		return null;
	}
	
	private String getValue(Expression e)
	{
		String type=e.getType();
		if(isUnary(type))
		{
			return getValue(e.getSubexpression());
		}
		else if(whichBinary(type)!=null)
		{
			return getValue(e.getSubexpression("left"))+" "+e.getType()+" "+getValue(e.getSubexpression("right"));
		}
		else
		{
			return e.getValue();
		}
		
	}


}
