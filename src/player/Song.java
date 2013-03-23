package player;

import java.util.ArrayList;

public class Song {
	
	
	ArrayList<Voice> voices = new ArrayList<Voice>();
	
	
	public void add(Voice v){
	    voices.add(v);
	}
	
	/**
	 * Get the minimum number of ticks per quarter needed to represent everything in the voice.
	 * @return an int representing the min ticks/quarter that would support this bar.
	 */	
	public int getMinTicksPerQuarter(){
	    int minTicks = 1;
	    for (int i = 0; i< voices.size(); i++){
	        int min = voices.get(i).getMinTicksPerQuarter(); 
	        if (min > minTicks){
	            minTicks = min;
	        }
	    }
	    return minTicks;
	}
	
	/**
     * Adds everything in the song to the player.
     * 
     * Calls addToPlayer on all the voices in its list
     * Updates the tick, and returns its value at the end.
     * @param player - the myPlayer object that all notes should be added to.
     */
	public void addToPlayer(myPlayer player){
	    
	    int ticksPerQuarter = this.getMinTicksPerQuarter();
	    System.out.println("Ticks Per Quarter: "+ticksPerQuarter);
        for (int i = 0; i<voices.size(); i++){
            voices.get(i).addToPlayer(0,ticksPerQuarter, player);
        }
	}	
	
}
