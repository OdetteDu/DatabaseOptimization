package controller;

public class TableInfo implements Comparable<TableInfo> {
	
	private String id;
	private int numTuple;
	
	public TableInfo(String id, int numTuple)
	{
		this.id=id;
		this.numTuple=numTuple;
	}

	public int getNumTuple()
	{
		return numTuple;
	}
	
	public String getId()
	{
		return id;
	}
	
	@Override
	public int compareTo(TableInfo other) {
		
		return other.getNumTuple()-this.numTuple;
	}
	
	public String toString()
	{
		return "The table with id="+id+" has "+numTuple+" tuples.";
	}

}
