package compile;
import java.util.ArrayList;


public class ExpressionDecomposer {

	private ArrayList<String> identifiers;

	public ExpressionDecomposer()
	{
		
	}
	
	public ArrayList<String> getIdentifiers(Expression e)
	{
		identifiers=new ArrayList<String>();
		processIdentifiers(e);
		return identifiers;
	}

	private void processIdentifiers(Expression e)
	{
		String type=e.getType();
		
		if(type.equals("identifier"))
		{
			identifiers.add(e.getValue());
		}

		for (int i = 0; i < Expression.unaryTypes.length; i++) 
		{
			if (type.equals (Expression.unaryTypes[i])) 
			{
				processIdentifiers(e.getSubexpression());
			}
		} 
		
		for (int i = 0; i < Expression.binaryTypes.length; i++) 
		{
			if (type.equals (Expression.binaryTypes[i])) 
			{
				processIdentifiers(e.getSubexpression("left"));
				processIdentifiers(e.getSubexpression("right"));
			}
		} 
	}
}
