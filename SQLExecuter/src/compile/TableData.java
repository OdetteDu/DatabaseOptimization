package compile;

import java.util.*;
import java.util.Map.Entry;

/**
 * This little class is used to hold the catalog information about a database table.
 * A bunch of these objects in a Map <String, TableData> is used to store the catalog.
 */

public class TableData implements Iterable<AttInfo>{

	private String name;
	//private int tupleCount;
	protected Map <String, ? extends AttInfo> attributes;

	public TableData (String name,Map <String, ? extends AttInfo> attsIn) {
		this.name=name;
		//tupleCount = numTuples;
		attributes = attsIn;
	}

	/*
	public int getTupleCount () {
		return tupleCount; 
	}
	*/
	
	public int getAttrCount()
	{
		return attributes.size();
	}

	public AttInfo getAttInfo (String aboutMe) {
		return attributes.get (aboutMe); 
	}
	
	public String getName()
	{
		return name;
	}

	
	public String print () {
		String res = "Table: "+name+"; atts are {";
		for (Entry<String, ? extends AttInfo> j : attributes.entrySet ()) {
			res += "(" + j.getKey () + ": " + j.getValue ().print () + ")";
		}
		res += "}";
		return res;
	}
	

	@Override
	public Iterator<AttInfo> iterator() 
	{
		return new Iterator<AttInfo>()
		{
			Set<String> s=attributes.keySet();
			Iterator<String> i=s.iterator();
			
			@Override
			public boolean hasNext() 
			{
				return i.hasNext();
			}

			@Override
			public AttInfo next() 
			{
				return attributes.get(i.next());
			}

			@Override
			public void remove() 
			{
				// TODO Auto-generated method stub
			}
		};
	}
}
