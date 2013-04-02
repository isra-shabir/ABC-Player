package player;

import java.util.ArrayList;

public class Voice {
	
	
	private String name;
	private int startPoint = 0;
	
	ArrayList<Bar> bars = new ArrayList<Bar>();
	
	/**
	 * Constructs a Voice object.
	 * @param name
	 */
	public Voice(String name){
	    this.name = name;
	}
	
	public boolean isNamed(String name){
	    return this.name == name;
	}
	
	/**
	 * Adds a bar to the voice.
	 * @param bar - a bar object.
	 */
	public void add(Bar bar){
	    bars.add(bar);
	}
	
	/**
	 * Get the minimum number of ticks per quarter needed to represent everything in the voice.
	 * @return minTicks - an int representing the minimum number of ticks per quarter needed to represent everything in the voice.
	 */	
	public int getMinTicksPerQuarter(){
	    int minTicks = 1;
	    for (int i = 0; i< bars.size(); i++){
	        int min = bars.get(i).getMinTicksPerQuarter(); 
	        minTicks = lowestCommonMultiple(minTicks, min);
	    }
	    System.out.println("mtpq of the voice: "+minTicks);
	    return minTicks;
	}
	
	/**
     * Returns the lowest common multiple of two numbers.
     * @param a, a positive non-zero int.
     * @param b, a positive non-zero int.
     * @return lcm, the largest number that is a multiple of both a and b.
     */
    private int lowestCommonMultiple(int a, int b){
        
        int big = a;
        int small = a;
        if (a>b){
            small = b;
        }
        else {
            big = b;
        }
        
        int bigi = big;
        while (big % small != 0){
            big = big + bigi;
        }
        return big;
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
        for (int i = 0; i<bars.size(); i++){
            currentTick = bars.get(i).addToPlayer(currentTick,ticksPerQuarter, player);
        }
        return currentTick;
	}	
	
	//Repeat Methods and Handling...
	
	/**
	 * @modify startPoint: changes it to last index.
	 */
	public void hitStart(){
		this.startPoint = bars.size();	
	}
	
	/**
	 * Repeats the segment of bars (from startPoint) in the list of bars.
	 * @modify barStructs: adds a bunch of bars to the end of barStruct
	 */
	public void repeat(){
	    int currentSize = bars.size();
		for (int i = startPoint; i < currentSize; i++){
		    bars.add(bars.get(i));
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
		    bars.remove(bars.size() -1);
		}
		//Add the new bars.
		for (int i=0; i<newBars.size(); i++){
		    bars.add(newBars.get(i));
		}
	}
	
	/**
	 * Returns a string representation of a voice.
	 */
	public String toString(){
	    String v = "";
	    v = v+"Voice of "+this.name;
	    for (int i = 0; i < this.bars.size(); i++){
	        v = v + this.bars.get(i).toString();
	    }
	    return v;
	}

}
