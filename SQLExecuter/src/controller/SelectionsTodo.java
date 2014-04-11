package controller;

import java.util.ArrayList;
import java.util.Iterator;

import compile.Expression;

public class SelectionsTodo {

	private String id;
	private ArrayList<Expression> exprs;
	
	
	public SelectionsTodo(String id)
	{
		this.id=id;
		exprs=new ArrayList<Expression>();
	}
	
	public void addExpression(Expression e)
	{
		exprs.add(e);
	}
	
	public String getId()
	{
		return id;
	}
	
	public ArrayList<Expression> getExpression()
	{
		return exprs;
	}
}
