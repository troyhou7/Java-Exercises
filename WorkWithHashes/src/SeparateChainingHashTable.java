
import java.util.LinkedList;
public class SeparateChainingHashTable 
{
	int m_nTableSize = 10000;
	LinkedList<DataObject>[] m_ObjectArray;
	private int elements;
	//make linked list with default size
	public SeparateChainingHashTable( )
    {
		m_ObjectArray = new LinkedList[m_nTableSize];
		elements = 0;
    }
	//make linked list with list size
    public SeparateChainingHashTable( int nTableSize )
    {
    	m_nTableSize = nTableSize;
    	m_ObjectArray = new LinkedList[m_nTableSize];
    	elements = 0;
    }
    //add items to hash table using separate chaining
    public void put( String strKey, DataObject objData )
    {
    	//expand not needed
    	
    	//get hash value from string and % ts
    	int hv = (int) Utility.HashFromString(strKey) % m_nTableSize;
    	//create new list if location doesnt have one yet
    	if( m_ObjectArray[hv] == null )
    		m_ObjectArray[hv] = new LinkedList <DataObject>();
    	//iterate through list at specified hash location
    	for(int i = 0; i < m_ObjectArray[hv].size(); i++)
    	{
    		//if current object contains key, remove object from list
    		//this avoids adding duplicates
    		if(m_ObjectArray[hv].get(i).GetKey() == strKey)
    		{
    			m_ObjectArray[hv].remove(i);
    			//decrement elements to account for removal
    			elements--;
    		}
    	}
    	//add object to list at hash location
    	m_ObjectArray[hv].add(objData);
    	elements++;
    }
    //get DataObject specified by string key using separate chaining
    public DataObject get( String strKey )
    {
    	//hash value of string key
    	int hv = (int) Utility.HashFromString(strKey) % m_nTableSize;
    	//iterate through list at specified hash location
    	for(int i = 0; i < m_ObjectArray[hv].size(); i++)
    	{
    		//if the current object contains the key, return the object
    		if(m_ObjectArray[hv].get(i).GetKey() == strKey)
    		{
    			return m_ObjectArray[hv].get(i);
    		}
    	}
    	//if the key is not found in the list, return null
    	return null;
    }
    //returns total number of elements throughout all lists
    public int size()
    {
    	return elements;
    }
  

}
