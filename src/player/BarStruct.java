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

	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player);

	
}
