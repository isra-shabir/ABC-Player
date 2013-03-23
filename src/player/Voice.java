package player;

import java.util.ArrayList;

public class Voice implements BarStruct {
	
	
	private String name;
	private int startPoint = 0;
	
	ArrayList<BarStruct> barStructs = new ArrayList<BarStruct>();
	
	/**
	 * Constructs a Voice object.
	 * @param name
	 */
	public Voice(String name){
	    this.name = name;
	}
	
	/**
	 * Adds a bar to the voice.
	 * @param bar - a bar object.
	 */
	public void add(BarStruct bar){
	    barStructs.add(bar);
	}
	
	/**
	 * Get the minimum number of ticks per quarter needed to represent everything in the voice.
	 * @return minTicks - an int representing the minimum number of ticks per quarter needed to represent everything in the voice.
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
     * Adds everything in the bar to the player.
     * 
     * Calls addToPlayer on all the structs in its list
     * Updates the tick, and returns its value at the end.
     * @param startTicking: an int representing the start tick of the bar.
     * @param ticksPerQuarter: number of ticks allocated to every quarter
     * @param player - the myPlayer object that all notes should be added to.
     */
	public int addToPlayer(int startingPoint, int ticksPerQuarter, myPlayer player){
	    int currentTick = startingPoint;
        for (int i = 0; i<barStructs.size(); i++){
            currentTick = barStructs.get(i).addToPlayer(currentTick,ticksPerQuarter, player);
        }
        return currentTick;
	}	
	
	//Repeat Methods and Handling...
	
	/**
	 * @modify startPoint: changes it to last index.
	 */
	public void hitStart(){
		this.startPoint = barStructs.size();	
	}
	
	/**
	 * Repeats the segment of bars (from startPoint) in the list of bars.
	 * @modify barStructs: adds a bunch of bars to the end of barStruct
	 */
	public void repeat(){
	    int currentSize = barStructs.size();
		for (int i = startPoint; i < currentSize; i++){
		    barStructs.add(barStructs.get(i));
		}
	}
	
	/**
	 * Replaces the last n bar with the argument
	 * @param bar that should be the n last bars. 
	 * 
	 */
	public void replaceLast(ArrayList<Bar> newBars){
		//Remove the last numReplace bars.
		for (int i=0; i<newBars.size(); i++){
		    barStructs.remove(barStructs.size() -1);
		}
		//Add the new bars.
		for (int i=0; i<newBars.size(); i++){
		    barStructs.add(newBars.get(i));
		}
	}

}
