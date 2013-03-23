package player;

import java.util.ArrayList;

public class Song {
	
	
	ArrayList<Voice> voices = new ArrayList<Voice>();
	
	
	public void add(Voice v){
	    voices.add(v);
	}
	
	/**
	 * Get the minimum number of ticks per quarter needed to represent everything in the voice.
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
	 * 
	 */
	public void addToPlayer(myPlayer player){
	    
	    int ticksPerQuarter = this.getMinTicksPerQuarter();
	    System.out.println("Ticks Per Quarter: "+ticksPerQuarter);
        for (int i = 0; i<voices.size(); i++){
            voices.get(i).addToPlayer(0,ticksPerQuarter, player);
        }
	}	
	
}
