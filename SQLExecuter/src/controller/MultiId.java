package controller;

public class MultiId {

	private String id1;
	private String id2;
	
	public MultiId(String id1, String id2)
	{
		this.id1=id1;
		this.id2=id2;
	}
	
	public String getId1()
	{
		return id1;
	}
	
	public String getId2()
	{
		return id2;
	}
	
	public boolean equals(Object o)
	{
		MultiId mi=(MultiId)o;
		return (this.id1.equals(mi.getId1())&&this.id2.equals(mi.getId2()))||
				(this.id1.equals(mi.getId2())&&this.id2.equals(mi.getId1()));		
	}
	
	public int hashCode()
	{
		return id1.hashCode()+id2.hashCode();
	}
}
