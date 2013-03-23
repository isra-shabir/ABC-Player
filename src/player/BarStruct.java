package player;

public interface BarStruct {
	
	/**
	 * @return
	 */
	
	public int getMinTicksPerQuarter();
	
	/** 
	 * @param startTicking
	 * @return
	 */
	
	// add argument player
<<<<<<< HEAD:src/player/BarStruct.java
	public int addToPlayer(int startTicking, myPlayer player);
=======
	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player);
>>>>>>> Built Bar, and fixed the addToPlayer methods and ticksPerBar retrieval:src/player/Barstruct.java

	
}
