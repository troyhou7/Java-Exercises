// GraphSearch.java
// ================
// This is a framework for initializing a graph from an input file that contains
// an adjacency matrix. The first line of the input file indicates how many
// vertices are in the graph. The following n lines contain the adjacency matrix
// for the (zeros and ones). For example:
//
//    4
//    0 1 0 1
//    1 0 1 1
//    0 1 0 1
//    1 1 1 0
//
// ...which corresponds to the unweighted, undirected graph:
//
//    0---1
//     \ /|
//      X |
//     / \|
//    2---3
//
// These values are read into a boolean matrix, and we perform breadth-first and
// depth-first search traversals starting from an arbitrary vertex.
//
// The comments in this file are sparse in some places because I've already
// explained this code in class. In other places, the comments are unusually
// verbose because I want you to have a written copy of some of the comments I
// made about this code. In your programming assignments, please do not emulate
// the unbalanced commenting style I am employing here. :)

import java.io.File;
import java.util.*;

public class GraphSearch
{
	
	boolean [][] bMatrix;
	
	public GraphSearch(String filename) throws Exception
	{
		Scanner in = new Scanner(new File(filename));
		int N = in.nextInt();
		
		bMatrix = new boolean[N][N];
		
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				bMatrix[i][j] = (in.nextInt() == 1);
			}
		}
		
		in.close();
	}
	
	// Iterative BFS method.
	public void BFS(int nStart)
	{
		// Iterative BFS places vertices in a queue. When we pull a vertex out of
		// the queue, we process it (in this case, print it to the screen), then
		// place all its unvisited neighbors in the queue. We mark each vertex as
		// visited as it goes into the queue, because that ensures we never place
		// a vertex into the queue more than once. (That can significantly reduce
		// the space complexity of this algorithm when dealing with a large, dense
		// graph.)
		
		// A queue is an abstract data type. We need to decide what underlying
		// data structure to use to implement it. Java will not allow you to do,
		// e.g.: Queue<Integer> q = new Queue<Integer>();
		Queue<Integer> objQueue = new LinkedList<Integer>();
		boolean [] bVisited = new boolean[bMatrix.length];
		
		// Start by adding the start vertex to the queue. It will be the first
		// thing dequeued, at which point we'll print it and add its neighbors
		// to the queue.
		objQueue.add(nStart);
		bVisited[nStart] = true;
		
		while (!objQueue.isEmpty())
		{
			// Remove a node from the queue and process it. If we were searching
			// for a particular node (a "goal"), this is where we would check
			// whether we had found it. If so, we would terminate the BFS. However,
			// since the goal of this BFS method is simply to print all nodes of a
			// graph in BFS order, we simply print this node and move on.
			int nNode = objQueue.remove();
			System.out.println(nNode);
			
			// Add all neighbors of 'node' to the queue (as long as they haven't
			// been visited already).
			for (int i = 0; i < bMatrix.length; i++)
			{
				if (bMatrix[nNode][i] && !bVisited[i])
				{
					bVisited[i] = true;
					objQueue.add(i);
				}
			}
		}
	}
	
	// Wrapper to our recursive DFS method. This one sets up the 'visited' array.
	public void DFS(int nStart)
	{
		// Recall that I mentioned the Arrays.fill() method here. If we want to
		// fill a boolean array with all 'true' values, we could use, e.g.,
		// Arrays.fill(myArray, true).
		boolean [] bVisited = new boolean[bMatrix.length];
		DFS(nStart, bVisited);
	}
	
	private void DFS(int nNode, boolean [] bVisited)
	{
		// As soon as we encounter a node in our DFS traversal, we mark it as
		// visited. This ensures that we won't get stuck in an infinite loop if
		// our graph has a cycle.
		bVisited[nNode] = true;
		
		// If we were searching for a particular vertex (a "goal"), this is where we
		// would check whether we have found the goal. If so, we would terminate the
		// DFS. However, since the purpose of this particular method is simply to
		// print all vertices in DFS order, we just print this vertex and move on.
		System.out.println(nNode);
		
		// Now call DFS() on all of this node's neighbors that haven't already
		// been visited.
		for (int i = 0; i < bMatrix.length; i++)
		{
			if (bMatrix[nNode][i] && !bVisited[i])
			{
				DFS(i, bVisited);
			}
		}
	}
	
	public static void main(String [] args) throws Exception
	{
		GraphSearch g = new GraphSearch("g1.txt");
		System.out.println("BFS(0):"); g.BFS(0);
		System.out.println("DFS(0):"); g.DFS(0);
		System.out.println("DFS(3):"); g.DFS(3);
	}
}
