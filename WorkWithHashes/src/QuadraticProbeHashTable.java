
public class QuadraticProbeHashTable {
	
	//default size
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	private int elements;
	//make object array with default size
	public QuadraticProbeHashTable()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
		elements = 0;
	}
	//make object array with list size
	public QuadraticProbeHashTable(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
		elements = 0;
	}
	//increase object array size if needed
	public void Expand()
	{
		if( elements < m_nTableSize /2 ) 
			return;
		//makes sure size is a prime number for quadratic
		int newsize = Utility.nextPrime(m_nTableSize *2);
		//store old array in new array
		DataObject[] newlist = m_ObjectArray;
		//make new array with new size
		m_nTableSize = newsize;
		m_ObjectArray = new DataObject[newsize];
		//elements to 0, will be updated with next loop and put
		elements = 0;
		//put objects from old list into new list
		for( int i=0; i<newlist.length; i++ )
		{
			if( newlist[i] == null ) 
				continue;
			put( newlist[i].GetKey(), newlist[i]);
		}
	}
	//add item to hash table using quadratic probing
	public void put( String strKey, DataObject objData )
	{
		//if( elements < m_nTableSize /2 )
		Expand();
		//hash value from string
		int hv = (int) Utility.HashFromString(strKey);
		int q = 0;
		//find spot using quadratic probing
		while( m_ObjectArray[(hv + q * q) %m_nTableSize] != null && 
				!m_ObjectArray[(hv + q * q) %m_nTableSize].GetKey().equals(strKey))
		{
			//hv++;
			q++;
		}
		//increase element count if non duplicate
		if( m_ObjectArray[(hv + q * q) %m_nTableSize] == null ) 
			elements++;
		//store object at spot found
		m_ObjectArray[(hv + q * q) % m_nTableSize] = objData;
	}
	//get DataObject specified by string key using quadratic probing
	public DataObject get( String strKey )
	{	
		//hash value from string
		int hv = (int) Utility.HashFromString(strKey);
		int q = 0;
		//find object using quadratic probing
		while( m_ObjectArray[(hv + q * q) %m_nTableSize] != null && 
				m_ObjectArray[(hv + q * q) %m_nTableSize].GetKey() != strKey)
		{
			q++;
		}
		//key not found, return null
		if( m_ObjectArray[(hv + q * q) %m_nTableSize] == null ) 
			return null;
		//return data object for key
		return( m_ObjectArray[(hv + q * q) %m_nTableSize] );
	}
	//returns number of elements in array
    public int size()
    {
    	return elements;
    }

}
