import java.util.*;

public class PlayerCharacter 
{
	//This class shall be used to represent Player Characters as data.
	//D&D aficionados will note the bare nature of this class and the paucity of given stats.
	//However, for our purposes, the given information will work just fine.
	
	//For consistency and ease of coding, this class was completed for you.
	//In the immortal words of the Bluth family, that was a freebie.
	
	//For our purposes, these values will represent all possible characters.
	
	private int[] baseStatScores;
	private int level;
	private String type;
	
	//I was gonna call these by their proper name, "classes", but that word is special in java.
	static String[] types =
	{
		"Barbarian", "Bard", "Cleric", "Druid",
		"Fighter", "Monk", "Paladin", "Ranger",
		"Rogue", "Sorcerer", "Warlock", "Wizard"
	};
	
	//Constructs a PlayerCharacter of specific level and type.
	public PlayerCharacter(int lev, int ty)
	{
		baseStatScores = rollBaseStats();
		level = lev;
		type = types[ty%12];
	}
	
	//Constructs a PlayerCharacter of specific level and type.	
	public PlayerCharacter(int lev, String ty)
	{
		baseStatScores = rollBaseStats();
		level = lev;
		type = ty;
	}
	
	//Constructs an entirely random character.
	public PlayerCharacter()
	{
		baseStatScores = rollBaseStats();
		level = (int)Math.ceil(Math.random() * 20);
		type = types[(int)Math.floor(Math.random() * 12)];
	}
	
	//Gets this character's level.
	public int getLevel()
	{
		return level;
	}
	
	//Sets this character's level.
	public void setLevel(int lev)
	{
		level = lev;
	}
	
	//Gets this character's type.
	public String gettype()
	{
		return type;
	}
	
	//Sets this character's type.
	public void settype(int ty)
	{
		type = types[ty];
	}
	
	//Sets this character's type.	
	public void settype(String ty)
	{
		type = ty;
	}

	//Gets this character's stats.
	public int[] getBaseStats()
	{
		return baseStatScores;
	}
	
	//Sets this character's stats.
	//To prevent creating broken characters, you cannot directly set the stats.
	public void setBaseStats()
	{
		baseStatScores = rollBaseStats();
	}
	
	//Randomizes the character's level.
	public void randomizeLevel()
	{
		level = (int)Math.ceil(Math.random() * 20);
	}
	
	//Randomizes the character's type.
	public void randomizeType()
	{
		type = types[(int)Math.floor(Math.random() * 12)];
	}
	
	//Rolls character stats.
	private int[] rollBaseStats()
	{
		int stats[] = new int[6];
		for(int i = 0; i<6; i++)
		{
			int array[] = new int[4];  
			int sum = 0;
            for(int j = 0; j<4; j++)
            {
                array[j] = (int)Math.ceil(Math.random() * 6);
                sum += array[j];
            }
            int min = array[0];

            for(int j = 0; j<4; j++)
            {
                if(array[j] < min) min = array[j];
            }

            sum -= min;
            
            stats[i] = sum;
		}
		
		return stats;
	}
	
	//Gives an easy way to print the character.
	//This way, you can just call System.out.println(x), where x is a PlayerCharacter object.
	public String toString()
	{
		return "Level " + level + " " + type;
	}
}
