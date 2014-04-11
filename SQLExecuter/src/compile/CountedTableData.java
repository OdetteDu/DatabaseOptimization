package compile;
import java.util.*;
import java.util.Map.Entry;

/**
 * This little class is used to hold the catalog information about a database table.
 * A bunch of these objects in a Map <String, TableData> is used to store the catalog.
 */

public class CountedTableData extends TableData{

	private int tupleCount;

	public CountedTableData (String name,int numTuples, Map <String, CountedAttInfo> attsIn) {
		super(name, attsIn);
		tupleCount = numTuples;
	}

	public int getTupleCount () {
		return tupleCount; 
	}

	public String print () {
		String res = tupleCount + " tuples; atts are {";
		for (Entry<String, ? extends AttInfo> j : attributes.entrySet ()) {
			res += "(" + j.getKey () + ": " + j.getValue ().print () + ")";
		}
		res += "}";
		return res;
	}
}