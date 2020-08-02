
public class DataObject 
{
	public DataObject(String strKey)
	{
		m_strKey = strKey;
	}
	
	String m_strKey = "";
	// Other data here.
	
	public String GetKey() 
	{
		return( m_strKey );
	}
}
