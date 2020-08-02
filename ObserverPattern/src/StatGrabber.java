import java.util.ArrayList;

public class StatGrabber implements Subject 
{
	//list of accounts getting notifications
	private ArrayList<Observer> observers;
	
	//stats being observed/updated
	private int subCount;
	private int vidCount;
	private int viewerCount;
	
	public StatGrabber()
	{
		observers = new ArrayList<Observer>();
	}
	//register account to receive notifications
	@Override
	public void register( Observer newObserver ) 
	{
		observers.add( newObserver );
	}
	//unregister account, will no longer get notifications
	@Override
	public void unregister( Observer deleteObserver ) 
	{
		int observerIndex = observers.indexOf( deleteObserver );
		observers.remove(observerIndex);
	}
	//update each account
	@Override
	public void notifyObserver() 
	{
		for( Observer observer : observers )
		{
			observer.update( subCount, vidCount, viewerCount );//stats
		}
	}
	//set stats and notify observer of updates
	public void setSubCount( int newSubCount )
	{
		this.subCount = newSubCount;
		notifyObserver();
	}
	public void setVidCount( int newVidCount )
	{
		this.vidCount = newVidCount;
		notifyObserver();
	}
	public void setViewerCount( int newViewerCount )
	{
		this.viewerCount = newViewerCount;
		notifyObserver();
	}
}
