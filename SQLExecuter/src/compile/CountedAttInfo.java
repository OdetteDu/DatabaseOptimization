package compile;

/**
 * This little class holds the catalog info about an attribute.
 * 
 * Everything is pretty self explanatory, except for the "seqNumber"
 * field, which stores whether this is the first, second, third, etc., attribute
 * in the table to which this attribute belongs.
 */
public class CountedAttInfo extends AttInfo{
 
  //private String name;
  private int valueCount;
  //private String dataType;
  //private int seqNumber;
  
  public CountedAttInfo (String name, int numDistinctVals, String myType, int whichAtt) {
	  super(name, myType, whichAtt);
    valueCount = numDistinctVals;
    
  }
  
  public int getNumDistinctVals () {
    return valueCount; 
  }
  
  String print () {
    return "vals: " + valueCount + "; type: " + dataType + "; attnum: " + seqNumber;  
  }
}