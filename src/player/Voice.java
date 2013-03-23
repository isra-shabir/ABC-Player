package player;

import java.util.ArrayList;

public class Voice implements BarStruct {
	
	
	private String name;
	private int startPoint = 0;
	
	ArrayList<BarStruct> barStructs = new ArrayList<BarStruct>();
	
	public Voice(String name){
	    this.name = name;
	}
	
	public void add(BarStruct bar){
	    barStructs.add(bar);
	}
	
	/**
	 * Get the minimum number of ticks per quarter needed to represent everything in the voice.
	 */	
	public int getMinTicksPerQuarter(){
	    int minTicks = 1;
	    for (int i = 0; i< barStructs.size(); i++){
	        int min = barStructs.get(i).getMinTicksPerQuarter(); 
	        if (min > minTicks){
	            minTicks = min;
	        }
	    }
	    return minTicks;
	}
	
	/**
	 * 
	 */
	public int addToPlayer(int sT, int tPQ, myPlayer player){
	    int ticksPerQuarter = getMinTicksPerQuarter();
	    int currentTick = 0;
        for (int i = 0; i<barStructs.size(); i++){
            currentTick = barStructs.get(i).addToPlayer(currentTick,ticksPerQuarter, player);
        }
        return currentTick;
	}	
	
	
	
	//Repeat Methods and Handling...
	/**
	 * 
	 */
	public void hitStart(){
		this.startPoint = barStructs.size();	
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
