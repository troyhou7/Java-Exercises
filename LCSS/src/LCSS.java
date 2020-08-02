import java.util.ArrayList;
import java.util.Scanner;
/*
 * Troy Houston
 * COP 3502C
 * LCSS Assignment
 * 04/05/16
 */
public class LCSS 
{
	//	This code was designed around copy/pasting input text into the console, 
	//	formatted like the Sample input on the Webcourses LCSS assignment 
	//	description page.
	
	//	Probably due to how I am scanning in the strings, the program will 
	//	run immediately after copy/paste (without pressing enter).
	//	When this happens, the program will stop before executing the 
	//	last case, but then pressing enter should finish everything
	//	properly.
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		int nCases = 0;
		if(scanner.hasNextInt())
			nCases = scanner.nextInt();	
		else
			System.out.println("\nInvalid input");
		for( int n = 0; n < nCases; n++ )
		{
			System.out.println("\nCase " + (n + 1) + ":");
			String str1 = "";
			//gets string, accounts for blank lines
			while( str1.equals("") && scanner.hasNextLine() )
			{
				str1 = scanner.nextLine();
			}
			//gets other string
			String str2 = "";
			while( str2.equals("") && scanner.hasNextLine())
			{
				str2 = scanner.nextLine();
			}
			
			ArrayList<String> sequence1 = new ArrayList<String>();
			StringBuilder build = new StringBuilder();
			
			//adds each individual substring to array list
			for( int i = 0; i < str1.length(); i++ )
			{
				if( str1.charAt(i) != ' ' )
				{
					build.append( str1.charAt(i) );
					//skips last substring without this
					if(i < str1.length() - 1)
						continue;
				}
				sequence1.add(build.toString());
				build = new StringBuilder();
			}  
			
			ArrayList<String> sequence2 = new ArrayList<String>();
			//adds each substring to second array list
			for( int i = 0; i < str2.length(); i++ )
			{
				if( str2.charAt(i) != ' ' )
				{
					build.append( str2.charAt(i) );
					//skips last substring without this
					if(i < str2.length() - 1)
						continue;
				}
				sequence2.add(build.toString());
				build = new StringBuilder();
			}
			//graph of individual substrings
			int[][] graph = new int[sequence1.size() + 1][sequence2.size() + 1];
			//LCSS length algorithm, value will be pushed to last index of 2D array
			for ( int i = 1; i < sequence1.size() + 1; i++ ) 
			{
				for ( int j = 1; j < sequence2.size() + 1; j++ ) 
				{
					//substrings equal, add to sum
					if ( sequence1.get(i - 1).equals( sequence2.get(j - 1) ) )
					{
						graph[i][j] = graph[i - 1][j - 1] + 1;
					}
					//shift larger sum (from left or above) to current index
					else 
					{
						if(graph[i - 1][j] > graph[i][j - 1])
						{
							graph[i][j] = graph[i - 1][j];
						}
						else
						{
							graph[i][j] = graph[i][j - 1];
						}
					}
				}
			}

			ArrayList<String> pLCSS = new ArrayList<String>();
			int i = sequence1.size();
			int j = sequence2.size();
			//goes through graph, finds substrings used in LCSS, adds them to LCSS array list
			while( i > 0 && j > 0 ) 
			{ 
				//substrings equal, add to list and jump diagonal on graph
				if ( sequence1.get(i - 1).equals( sequence2.get(j - 1) ) )
				{
					pLCSS.add(0, sequence1.get(i - 1));
					i--;
					j--;
				}
				//not equal, shift left or up on graph
				else if( graph[i - 1][j] > graph[i][j - 1] )
					i--;
				else
					j--;
			}	 
			//print LCSS length
			System.out.println("LCSS Length = " + graph[sequence1.size()][sequence2.size()]);
			//print LCSS 
			System.out.print("LCSS = ");
			for( int q = 0; q < pLCSS.size(); q++ )
			{
				System.out.print(pLCSS.get(q));
				if( q < pLCSS.size() - 1 )
					System.out.print(" ");
			}
		}
		scanner.close();
	}
}

	

