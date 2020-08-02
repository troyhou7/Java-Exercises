//Creates New account
public class StatObserver implements Observer
{
	private int subCount;
	private int vidCount;
	private int viewerCount;
	
	//keeps track of sleep mode
	private boolean isAsleep;
	public void setSleepStatus( boolean onOff )
	{
		this.isAsleep = onOff;
		if( this.isAsleep )
			System.out.println("Account " + this.observerID + " Sleep Mode turned on. This account will no longer receive notifications.");
		else
			System.out.println("Account " + this.observerID + " Sleep Mode turned off. This account will now receive notifications.");
	}
	public boolean getSleepStatus()
	{
		return this.isAsleep;
	}
	public int getAccountNum()
	{
		return this.observerID;
	}
	
	private static int observerIDTracker = 0;
	//individual account number
	private int observerID;
	
	private Subject statGrabber;
	
	public StatObserver( Subject statGrabber )
	{
		//unique account id
		this.statGrabber = statGrabber;
		this.observerID = ++observerIDTracker;
		
		System.out.println( "New account created: Account " + this.observerID );
		
		//register account for notifications
		statGrabber.register( this );
	}
	//update and notify account of updates
	@Override
	public void update( int subCount, int vidCount, int viewerCount )
	{
		this.subCount = subCount;
		this.vidCount = vidCount;
		this.viewerCount = viewerCount;
		
		printStats();	
	}

	public void printStats()
	{
		System.out.println( "\nAccount " + observerID + " notification:"+ "\nSubscriber Count: " + 
							subCount + "\nVideo Count: " + vidCount + "\nViewer Count: " + viewerCount );
	}
}
