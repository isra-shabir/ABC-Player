package player;

public interface BarStruct {
	
	/**
	 * @return
	 */
	
	public int getMinTicksPerQuarter();
	
	/** 
	 * 
	 * @param startTicking
	 * 
	 */

	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player);

	
}
