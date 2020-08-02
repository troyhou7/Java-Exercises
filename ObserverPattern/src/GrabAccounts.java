import java.util.ArrayList;
import java.util.Scanner;
/*
 * Troy Houston
 * COP 3502C
 * Observer Design Pattern
 * 4/13/16
 */
public class GrabAccounts 
{
	private static ArrayList<StatObserver> accounts = new ArrayList<StatObserver>(); 
	public static void main( String[] args )
	{
		//Youtube Account subject being tracked, this is where the stats are updated
		StatGrabber statGrabber = new StatGrabber();
		Scanner in = new Scanner( System.in );
		while( true )
		{
			System.out.println("Main Menu:");
			System.out.println("1. Create Account");
			System.out.println("2. Toggle SleepMode on Account");
			System.out.println("3. Edit Youtube Account Info.");
			System.out.println("4. Delete Account");
			System.out.println("5. Exit");
			int c;
			//makes sure input is valid
			do
			{
				System.out.println( "Please enter an integer 1 - 5" );
				while( !in.hasNextInt() )
				{
					System.out.println("Invalid input, try again");
					in.next();
				}
				c = in.nextInt();
			} while( c < 1 || c > 5 );
		
			if( c == 5 )
			{
				System.out.println("Exiting program");
				in.close();
				System.exit(0);
			}
			else if( c == 1 )
			{
				//creates new account and sets it up for notifications
				accounts.add( new StatObserver( statGrabber ) );
			}
			//toggle sleep mode
			else if( c == 2 )
			{
				//return to menu if no accounts
				if( accounts.size() == 0 )
				{
					System.out.println("No accounts exist!");
					continue;
				}
				
				System.out.println( "For which account would you like to toggle sleep mode?" );
				for( int i = 0; i < accounts.size(); i++ )
				{
					System.out.println( (i + 1) + ". Account " +  accounts.get(i).getAccountNum() );
				}
				int a;
				//makes sure input is valid
				do
				{
					System.out.println( "Please enter an integer 1 - " + accounts.size() );
					while( !in.hasNextInt() )
					{
						System.out.println("Invalid input");
						in.next();
					}
					a = in.nextInt();
				} while( a < 1 || a > accounts.size() );
				//account is asleep, toggle sleep mode off
				//re-registers observer to get notifications
				if( accounts.get( a - 1 ).getSleepStatus() )
				{
					statGrabber.register( accounts.get( a - 1 ) );
					accounts.get( a - 1 ).setSleepStatus( false );
				}
				//unregisters account from notifications (toggles sleep mode on)
				//account still exists in array list
				else
				{
					statGrabber.unregister( accounts.get( a - 1 ) );
					accounts.get( a - 1 ).setSleepStatus( true );
				}
			}
			//edit youtube account info
			else if( c == 3 )
			{
				System.out.println("Input new Subscriber Count: ");
				int s;
				while( !in.hasNextInt() )
				{
					System.out.println("Invalid input, enter an integer");
					in.next();
				}
				s = in.nextInt();
				statGrabber.setSubCount( s );
				
				System.out.println("Input new Video Count: ");
				while( !in.hasNextInt() )
				{
					System.out.println("Invalid input, enter an integer");
					in.next();
				}
				s = in.nextInt();
				statGrabber.setVidCount( s );
				
				System.out.println("Input new Viewer Count: ");
				while( !in.hasNextInt() )
				{
					System.out.println("Invalid input, enter an integer");
					in.next();
				}
				s = in.nextInt();
				statGrabber.setViewerCount( s );
			}
			//delete account
			else if( c == 4 )
			{
				//return to menu if no accounts
				if( accounts.size() == 0 )
				{
					System.out.println("No accounts exist!");
					continue;
				}
				System.out.println("Which account would you like to delete?");
				for( int i = 0; i < accounts.size(); i++ )
				{
					System.out.println( (i + 1) + ". Account " + accounts.get(i).getAccountNum() );
				}
				int d;
				//makes sure input is valid
				do
				{
					System.out.println( "Please enter an integer 1 - " + accounts.size() );
					while( !in.hasNextInt() )
					{
						System.out.println("Invalid input");
						in.next();
					}
					d = in.nextInt();
				} while( d < 1 || d > accounts.size() );
				//checking if account is already unregistered
				if( !accounts.get( d - 1 ).getSleepStatus() )
				{
					statGrabber.unregister( accounts.get( d - 1 ) );
				}
				//deletes account from list and prints confirmation
				System.out.println( "Account " + accounts.remove( d - 1 ).getAccountNum() + " deleted." );
			}
		}
	}
}
