package player;

import java.util.ArrayList;

public class Voice implements BarStruct {
	
	
	private String name;
	private int startPoint;
	public ArrayList<BarStruct> notestructs;
		
	
	/**
	 * 
	 */
	
	public int getMinTicksPerQuarter(){
		return 0;
	}
	/**
	 * 
	 */
	
	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player){
		return 0;
	}	
	
	/**
	 * 
	 */
	
	public void hitStart(){
		this.startPoint = notestructs.size();
		
	}
	
	/**
	 * 
	 */

	public void repeat(){
		
	}
	
	/**
	 * @param 
	 * 
	 */
	
	public void replaceLast(Bar bar){
		
	}
}
