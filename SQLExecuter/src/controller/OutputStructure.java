package controller;

import java.util.ArrayList;
import java.util.HashMap;

import compile.Expression;
import execute.AggFunc;
import execute.Attribute;

public class OutputStructure {
	
	private ArrayList <Attribute> outAtts;
	private HashMap <String, String> exprs;
	private HashMap <String, AggFunc> funcs;

	public OutputStructure(ArrayList <Attribute> outAtts,HashMap <String, String> exprs, HashMap <String, AggFunc> funcs)
	{
		this.outAtts=outAtts;
		this.exprs=exprs;
		this.funcs=funcs;
	}

	/**
	 * @return the outAtts
	 */
	public ArrayList <Attribute> getOutAtts() {
		return outAtts;
	}

	/**
	 * @param outAtts the outAtts to set
	 */
	private void setOutAtts(ArrayList <Attribute> outAtts) {
		this.outAtts = outAtts;
	}

	/**
	 * @return the exprs
	 */
	public HashMap <String, String> getExprs() {
		return exprs;
	}

	/**
	 * @param exprs the exprs to set
	 */
	private void setExprs(HashMap <String, String> exprs) {
		this.exprs = exprs;
	}

	public HashMap <String, AggFunc> getFuncs() {
		return funcs;
	}

	private void setFuncs(HashMap <String, AggFunc> funcs) {
		this.funcs = funcs;
	}
	
	
}
