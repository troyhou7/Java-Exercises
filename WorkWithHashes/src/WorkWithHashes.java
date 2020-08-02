import java.util.Hashtable;
/*
 * Troy Houston
 * COP 3503C-0013
 * 2/3/2016
 * Recitation Assignment 3
 */

public class WorkWithHashes
{
	public static void main(String[] args) 
	{
		//test hash table
		Hashtable<String, DataObject> ht = new Hashtable<String, DataObject>();
		
		for (String str : Lists.ListOne) 
		{
			ht.put(str, new DataObject( str ));
		} 
		//size should be same as each of the hashing methods below
		System.out.println(ht.size());
		
		//test hashes: will print each size, 
		//then will print results of 2 get cases
		linearProbeHashTable();
		
		quadraticProbeHashTable();
		
		separateChainingHashTable();
	}
	
	public static void linearProbeHashTable()
	{
		//Linear hash table with list 
		LinearHash hashTable = new LinearHash(Lists.ListOne.length);
		
		//iterate through list to add each element to table
		for(int i = 0; i < Lists.ListOne.length; i++)
		{
			//create data object using list item
			DataObject data = new DataObject(Lists.ListOne[i]);
			
			//add data to the hash table
			hashTable.put(Lists.ListOne[i], data);
		}
		//print size of linear hash table
		System.out.println(hashTable.size());
		//testing get 
		System.out.println(hashTable.get("Lucas"));  // should be a data object 
		System.out.println(hashTable.get("Lucas2")); //should be null
	}
	public static void quadraticProbeHashTable()
	{
		//Quadratic hash table with list
		QuadraticProbeHashTable hashTable = new QuadraticProbeHashTable(Lists.ListOne.length);
		
		//iterate through list to add each element to table
		for(int i = 0; i < Lists.ListOne.length; i++)
		{
			//create data object using list item
			DataObject data = new DataObject(Lists.ListOne[i]);
			
			//add data to the hash table
			hashTable.put(Lists.ListOne[i], data);
		}
		//print size of quadratic hash table
		System.out.println(hashTable.size());
		//testing get
		System.out.println(hashTable.get("Lucas"));  // should be a data object 
		System.out.println(hashTable.get("Lucas2")); //should be null
	}
	
	public static void separateChainingHashTable()
	{
		//separate chaining hash table with list
		SeparateChainingHashTable hashTable = new SeparateChainingHashTable(Lists.ListOne.length);
		
		//iterate through list to add each element to table
		for(int i = 0; i < Lists.ListOne.length; i++)
		{
			//create data object using list item
			DataObject data = new DataObject(Lists.ListOne[i]);
			
			//add data to the hash table
			hashTable.put(Lists.ListOne[i], data);
		}
		//print size of separate chaining hash table
		System.out.println(hashTable.size());
		//testing get
		System.out.println(hashTable.get("Lucas"));  // should be a data object 
		System.out.println(hashTable.get("Lucas2")); //should be null
		
	}
}
