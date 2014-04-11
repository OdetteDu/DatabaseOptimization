package controller;

import java.util.ArrayList;

import compile.Expression;

public class JoinsTodo {

	private MultiId mid;
	private ArrayList<Expression> exprs;
	
	public JoinsTodo(MultiId mid)
	{
		this.mid=mid;
		exprs=new ArrayList<Expression>();
	}
	
	public void addExpression(Expression e)
	{
		exprs.add(e);
	}
	
	public MultiId getID()
	{
		return mid;
	}
	
	public ArrayList<Expression> getExpression()
	{
		return exprs;
	}
	
	public String toString()
	{
		String s= "Id1="+mid.getId1()+" Id2="+mid.getId2();
		return s;
	}
}
