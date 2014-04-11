package compile;

/**
 * This little class holds the catalog info about an attribute.
 * 
 * Everything is pretty self explanatory, except for the "seqNumber"
 * field, which stores whether this is the first, second, third, etc., attribute
 * in the table to which this attribute belongs.
 */
public class AttInfo {
 
  private String name;
  //private int valueCount;
  protected String dataType;
  protected int seqNumber;
  
  public AttInfo (String name, String myType, int whichAtt) {
	  this.name=name;
    //valueCount = numDistinctVals;
    dataType = myType;
    seqNumber = whichAtt;
  }
  
  /*
  public int getNumDistinctVals () {
    return valueCount; 
  }
  */
  
  public String getDataType () {
    return dataType; 
  }
  
  public String getName()
  {
	  return name;
  }
  
  public int getAttSequenceNumber () {
    return seqNumber; 
  }
  
  
  String print () {
    return "vals: " + "unknown" + "; type: " + dataType + "; attnum: " + seqNumber;  
    //valueCount
  }
  
}