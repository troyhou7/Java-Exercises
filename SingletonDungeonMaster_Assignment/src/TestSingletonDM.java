import java.util.*;

public class TestSingletonDM 
{
	public static void main(String args[])
	{
		//This main function will be used to test your program.
		//Feel free to use it to test your code, but leave this blank when you turn your program in.
		
		//Some things you might want to test:
		
		//Creating two or more instances of AssembleParty, and starting their threads one after the other
		//Creating two or more instances of SingletonDM and comparing their IDs with System.identityHashCode()
		//Creating two or more instances of SingletonDM and comparing their sheet lists
		//Creating two or more instances of SingletonDM and getting sheets of only a certain type or level
		Runnable test = new AssembleParty();
		Runnable test1 = new AssembleParty();
		
		new Thread(test).start();
		new Thread(test1).start();


	
		
	}

}
