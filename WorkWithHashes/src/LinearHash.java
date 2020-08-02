
public class LinearHash 
{
	//default size
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	private int elements;
	//make object array with default size
	public LinearHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
		
		elements = 0;
	}
	//make object array with list size
	public LinearHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
		elements = 0;
	}
	//increases size of object array if needed
	public void Expand()
	{
		if( elements < m_nTableSize /2 ) 
			return;
		
		int newsize = m_nTableSize *2;
		//store old array in new array
		DataObject[] newlist = m_ObjectArray;
		//create new array with updated size
		m_nTableSize = newsize;
		m_ObjectArray = new DataObject[newsize];
		//elements will be recounted with next loop and put
		elements = 0;
		//put objects from old list into new list
		for( int i=0; i<newlist.length; i++ )
		{
			if( newlist[i] == null ) 
				continue;
			put( newlist[i].GetKey(), newlist[i]);
		}	
	}
	//add items to hash table using linear probing
	public void put( String strKey, DataObject objData )
	{
		//if( elements < m_nTableSize /2 )
		Expand();
		//hash value from string
		long lHash = Utility.HashFromString(strKey);
		//increment to find empty spot
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null && 
				!m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			lHash++;
		}
		//increase element count if non duplicate
		if( m_ObjectArray[(int)(lHash%m_nTableSize)] == null ) 
			elements++;
		//store object in table
		m_ObjectArray[(int)lHash%m_nTableSize] = objData;
	}
	
	//get DataObject specified by string key using linear probing
	public DataObject get( String strKey )
	{		
		//hash value from string key
		long lHash = Utility.HashFromString(strKey);
		//increment to find key
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null && 
				m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey() != strKey)
		{
			lHash++;
		}
		
		//if key isnt found return null
		if (m_ObjectArray[(int)(lHash%m_nTableSize)] == null) 
			return null;
		//return data object found
		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
	
	//returns number of elements in array
    public int size()
    {
    	return elements;
    }

}
