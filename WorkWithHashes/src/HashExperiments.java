import java.util.Hashtable;


public class HashExperiments 
{
	public static void main(String[] args) 
	{
		
		Hashtable<String,DataObject> lh = new Hashtable<String,DataObject>();
		
		// Start the clock.	
		long start = System.currentTimeMillis();
		
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			lh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
			
		}

		
		
		
		
		
		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
	}

}
