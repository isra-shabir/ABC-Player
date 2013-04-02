package data;

import java.util.ArrayList;

import player.myPlayer;

public class Song {
	
	ArrayList<Voice> voices = new ArrayList<Voice>();
	
	public Song(ArrayList<Voice> voices){
	    this.add(voices);
	}
	
	public Song(){
    
	}
	
	public void add(Voice v){
	    voices.add(v);
	}
	
	public void add(ArrayList<Voice> vs){
	    for (int i = 0; i < vs.size(); i++){
	        voices.add(vs.get(i));
	    }
	}
	
	/**
	 * Get the minimum number of ticks per quarter needed to represent everything in the voice.
	 * @return an int representing the min ticks/quarter that would support this bar.
	 */	
	public int getMinTicksPerQuarter(){
	    int minTicks = 1;
	    for (int i = 0; i< voices.size(); i++){
	        int min = voices.get(i).getMinTicksPerQuarter(); 
	        minTicks = lowestCommonMultiple(min, minTicks);
	        
	    }
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
