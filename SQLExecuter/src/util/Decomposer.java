package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import compile.AttInfo;
import compile.CountedAttInfo;
import compile.Expression;
import compile.CountedTableData;
import execute.AggFunc;

public class Decomposer {

	private Map<String, CountedTableData> database;
	private Map<String, String> from; 

	public Decomposer(Map<String, CountedTableData> database, Map<String, String> from)
	{
		this.database=database;
		this.from=from;
	}

	/**
	 * get the type after execute this expression
	 * for example, 1+1 the type is integer
	 * @param e the expression to be determined
	 * @return Int, Float, or Str, or Boolean, or Error
	 */
	public String getType(Expression e)
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
		else if(whichUnary(type)!=null)
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
					System.out.println("Error: (not) is operating on ("+getValueR(e)+") of type ("
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
					System.out.println("Error: "+e.getType()+" is operating on boolean ("+getValueR(e)
							+"), not numbers!");
					return "Error";
				}
			}
			else if(t.equals("Str"))
			{
				System.out.println("Error: "+e.getType()+" is operating on String ("+getValueR(e)+")");
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
					System.out.println("Warning: float is "+type+" an int");
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
									+getValueR(e.getSubexpression("left"))+") and ("
									+getValueR(e.getSubexpression("right"))
									+") is operating "+e.getType());
							return "Error";
						}
						else
						{
							if(left.equals("Str"))
							{
								System.out.println("Error: String ("+getValueR(e.getSubexpression("left"))+") is operating "+e.getType()
										+" with other types of data");
								return "Error";
							}
							else
							{
								System.out.println("Error: String ("+getValueR(e.getSubexpression("right"))+") is operating "+e.getType()
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
						System.out.println("Warning: String ("
								+getValueR(e.getSubexpression("left"))+") and ("
								+getValueR(e.getSubexpression("right"))
								+") is being compared using "+e.getType());
						return "Error";
					}
					else
					{
						//string with int
						if(left.equals("Int")||right.equals("Int"))
						{
							if(left.equals("Str"))
							{
								System.out.println("Error: String ("+getValueR(e.getSubexpression("left"))
										+") is being compared using "+e.getType()
										+" with Integer");
								return "Error";
							}
							else
							{
								System.out.println("Error: String ("+getValueR(e.getSubexpression("right"))
										+") is being compared using "+e.getType()+" with Integer");
								return "Error";
							}

						}
						//string with float
						else if(left.equals("Float")||right.equals("Float"))
						{
							if(left.equals("Str"))
							{
								System.out.println("Error: String ("+getValueR(e.getSubexpression("left"))
										+") is being compared using "+e.getType()
										+" with Float");
								return "Error";
							}
							else
							{
								System.out.println("Error: String ("+getValueR(e.getSubexpression("right"))
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
	public String whichUnary(String type)
	{
		for (int i = 0; i < Expression.unaryTypes.length; i++) 
		{
			if (type.equals (Expression.unaryTypes[i])) 
			{
				if(type.equals("sum")||type.equals("avg"))
				{
					return "aggregation";
				}
				else
				{
					return "unary";
				}
			}
		}
		return null;
	}

	/**
	 * determine which binary operator type is this type
	 * @param type
	 * @return null if it is not binary type
	 * 		   "operator" if it is one of "plus", "minus", "times","divided by"
	 *         "logic" if it is one of , "or", "and"
	 *         "compare" if it is "equals", "greater than", "less than"
	 */
	public String whichBinary(String type)
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

	public String getValue(Expression e)
	{
		String s = getValueR(e);
		if(s.charAt(0)=='(' && s.charAt(s.length()-1)==')')
		{
			return s.substring(1, s.length()-1);
		}
		return s;
	}

	public String getValue(Expression e, String left, String right,ArrayList <String> leftHash,
			ArrayList <String> rightHash)
	{
		String s = getValueR(e,left, right,leftHash, rightHash);
		if(s.charAt(0)=='(' && s.charAt(s.length()-1)==')')
		{
			return s.substring(1, s.length()-1);
		}
		return s;
	}

	private String getValueR(Expression e)
	{
		String type=e.getType();
		if(whichUnary(type)!=null)
		{
			if(whichUnary(type).equals("unary"))
			{
				return convertType(type)+" "+getValueR(e.getSubexpression());
			}
			else
			{
				//TODO need to use group
				return type+" ("+getValueR(e.getSubexpression())+")";
			}
		}
		else if(whichBinary(type)!=null)
		{
			return "("+getValueR(e.getSubexpression("left"))+" "+convertType(type)+" "+getValueR(e.getSubexpression("right"))+")";
		}
		else
		{
			if(type!="identifier")
			{
				String t=getType(e);
				return t+"("+e.getValue()+")";
			}
			else
			{
				String s=e.getValue();
				return s.substring(s.indexOf(".")+1, s.length());
			}
		}

	}

	private String getValueR(Expression e, String left, String right,ArrayList <String> leftHash,
			ArrayList <String> rightHash)
	{
		String type=e.getType();
		if(whichUnary(type)!=null)
		{
			if(whichUnary(type).equals("unary"))
			{
				return convertType(type)+" "+getValueR(e.getSubexpression(),left,right, leftHash, rightHash);
			}
			else
			{
				//TODO need to use group
				return type+" ("+getValueR(e.getSubexpression(),left,right, leftHash, rightHash)+")";
			}
		}
		else if(whichBinary(type)!=null)
		{
			return "("+getValueR(e.getSubexpression("left"),left,right, leftHash, rightHash)+" "+convertType(type)+" "+getValueR(e.getSubexpression("right"),left,right, leftHash, rightHash)+")";
		}
		else
		{
			if(type!="identifier")
			{
				String t=getType(e);
				return t+"("+e.getValue()+")";
			}
			else
			{
				String s=e.getValue();
				if(s.substring(0,s.indexOf('.')).equals(left))
				{
					if(!leftHash.contains(s.substring(s.indexOf(".")+1, s.length())))
						leftHash.add(s.substring(s.indexOf(".")+1, s.length()));
					return "left."+s.substring(s.indexOf(".")+1, s.length());
				}
				else if(s.substring(0,s.indexOf('.')).equals(right))
				{
					if(!rightHash.contains(s.substring(s.indexOf(".")+1, s.length())))
						rightHash.add(s.substring(s.indexOf(".")+1, s.length()));
					return "right."+s.substring(s.indexOf(".")+1, s.length());
				}
				else
				{
					return s.substring(s.indexOf(".")+1, s.length());
				}

			}
		}

	}

	public AggFunc getAggFunc(Expression e)
	{
		String type=e.getType();
		if(whichUnary(type)!=null)
		{
			if(whichUnary(type).equals("unary"))
			{
				return new AggFunc("none", convertType(type)+" "+getValueR(e.getSubexpression()));
			}
			else
			{
				return new AggFunc(e.getType(), getValueR(e.getSubexpression()));
			}
		}
		else if(whichBinary(type)!=null)
		{
			return new AggFunc("none", "("+getValueR(e.getSubexpression("left"))+" "+convertType(type)+" "+getValueR(e.getSubexpression("right"))+")");
		}
		else
		{
			if(type!="identifier")
			{
				String t=getType(e);
				return new AggFunc("none", t+"("+e.getValue()+")");
			}
			else
			{
				String s=e.getValue();
				return new AggFunc("none", s.substring(s.indexOf(".")+1, s.length()));
			}
		}

	}

	public String convertType(String type)
	{
		switch (type)
		{
		case "plus":
			return "+";
		case "minus":
			return "-";
		case "times":
			return "*";
		case "divided by":
			return "/";
		case "or":
			return "||";
		case "and":
			return "&&";
		case "not":
			return "!";
		case "unary minus":
			return "-";
		case "equals":
			return "==";
		case "greater than":
			return ">";
		case"less than":
			return "<";
		default:
			return "";
		}

	}

	public boolean andBreaker(Expression e, ArrayList<Expression> exprs)
	{
		if(e.getType().equals("and"))
		{
			if(!andBreaker(e.getSubexpression("left"),exprs))
			{
				//exprs.add(e.getSubexpression("left"));
			}
			exprs.add(e.getSubexpression("right"));
			return true;
		}
		else
		{
			exprs.add(e);
			return false;
		}
	}
	
	public void getAttributes(Expression e, HashMap<String, ArrayList<String>> attrsTable)
	{
		String type=e.getType();
		if(whichUnary(type)!=null)
		{
		
				getAttributes(e.getSubexpression(),attrsTable);
			
		}
		else if(whichBinary(type)!=null)
		{
			getAttributes(e.getSubexpression("left"),attrsTable);
			getAttributes(e.getSubexpression("right"),attrsTable);
		}
		else
		{
			if(type=="identifier")
			{
				String s=e.getValue();
				String t=s.substring(0, s.indexOf('.'));
				if(attrsTable.containsKey(t))
				{
					ArrayList<String> tt=attrsTable.get(t);
					String x=s.substring(s.indexOf('.')+1,s.length());
					if(tt.contains(x))
					{
						
					}
					else
					{
						attrsTable.get(t).add(x);
					}
					
				}
				else
				{
					ArrayList<String> a=new ArrayList<String>();
					a.add(s.substring(s.indexOf('.')+1,s.length()));
					attrsTable.put(t, a);
				}
			}
			else
			{
				
			}
		}
	}


}
