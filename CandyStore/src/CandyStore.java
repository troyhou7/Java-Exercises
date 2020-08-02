import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
/*
 * Troy Houston
 * COP 3503C
 * Candy Store 
 * 3/28/16
 */
public class CandyStore
{
	static int[] calories;
	static int[] prices;
	static int[][] solve;
	//to keep track of number of input sets
	static int count = 1;
	
	static void candyStore( String filename ) throws Exception
	{
		Scanner in = new Scanner( new File( filename ) );
		
		int nTypes = in.nextInt(); 
		double totalMoney = in.nextDouble();
		//file input specification
		while (nTypes != 0)
		{
			//10000 of each item should be enough
			int stock = nTypes * 10000;
			prices = new int[ stock ];
			calories = new int[ stock ];
			
			// *100 to account for cent values
			double money =  totalMoney * 100;
			//using a 2D array to find solution
			solve = new int[ stock + 1 ][ (int) money + 1 ];
			
			int i;
			//get calorie and price values from input.txt
			for( i = 0; i < nTypes; i++ )
			{
				calories[i] = in.nextInt();
				prices[i] = (int) (in.nextDouble() *100);
			}
			//fill rest of arrays with repeating values to stock candy store
			for( ; i < stock; i++ )
			{
				prices[i] = prices[i - nTypes];
				calories[i] = calories[i - nTypes];
			}
			//pushes largest possible sum of calories to last cell of 2D array
			for( i = 1; i <= stock; i++ )
			{
				for( int j = 0; j <= money; j++ )
				{
					if( prices[i-1] <= j )
					{
						solve[i][j] = Math.max(calories[i-1] + solve[i-1][j-prices[i-1]], solve[i-1][j]);
					}
					else
						solve[i][j] = solve[i - 1][j];
				}	
			}
			//display each result
			System.out.println("Max Calories for Input " + count++ + ": " + solve[stock][(int) money]);
			//next set of inputs from file
			nTypes = in.nextInt();
			totalMoney = in.nextDouble();
		}
		in.close();
	}
	public static void main(String[] args) throws Exception
	{
		candyStore("input.txt");
	}
}
