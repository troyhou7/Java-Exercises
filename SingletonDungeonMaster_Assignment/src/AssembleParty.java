import java.util.*;

public class AssembleParty implements Runnable 
{
	//This is a necessary component of testing the code.
	//For simplicity, it has been provided to you in a complete state.
	public void run()
	{
		System.out.println("Beginning this thread...\n");
		
		//Creates the only instance of SingletonDM, and prints the identity.
		SingletonDM singleton = SingletonDM.getInstance();		
		System.out.println("ID of current instance: " + System.identityHashCode(singleton));

		//Gets and prints all of the character sheets.
		LinkedList<PlayerCharacter> gameSheets = singleton.getSheetList();
		System.out.println("All sheets: " + gameSheets + "\n");
		
		//Get and prints only the level 1 character sheets.
		LinkedList<PlayerCharacter> levelOneGameSheets = singleton.getSheetsOfLevel(1);
		System.out.println("Only level 1 sheets: " + levelOneGameSheets + "\n");
		
		//Get and prints only the wizard character sheets.
		LinkedList<PlayerCharacter> wizardGameSheets = singleton.getSheetsOfType("Wizard");
		System.out.println("Only wizards: " + wizardGameSheets + "\n");
		
		//Confirms that the process is done.
		System.out.println("Character sheets retrieved!\n");
		
	}
}
