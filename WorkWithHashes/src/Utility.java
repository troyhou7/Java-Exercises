
public class Utility 
{
	
	//finds next prime number for quadratic expand
	public static int nextPrime(int n) 
	{
	    boolean isPrime = false;
	    while (!isPrime) 
	    {
	        n += 1;
	        int m = (int) Math.sqrt(n);

	        isPrime = true;
	        for (int i = 2; i <= m; i++) 
	        {
	            if (n % i == 0) 
	            {
	                isPrime = false;
	                break;
	            } 
	        }
	    }
		return n;
	}

	
	public static long HashFromString( String strString)
	{
		long lHashValue = 0;
		
		for( int i=0; i<strString.length(); i++ )
		{
			lHashValue = (long)strString.charAt(i) + (lHashValue << 6) + (lHashValue << 16) - lHashValue;
		}
		
		return( lHashValue & 0x7fffffff );
	}
	
	
}
