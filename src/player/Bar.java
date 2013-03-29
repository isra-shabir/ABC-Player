package player;

import java.util.ArrayList;

public class Bar{
	
    ArrayList<NoteStruct> noteStructs = new ArrayList<NoteStruct>();
    
   
    public void add(NoteStruct struct){
        this.noteStructs.add(struct);
    }
    
    /**
     * Returns the minimum number of Ticks per Quarter needed to display all the notes in the Bar. 
     * This is the maximum of the minTicksPerBar for each struct. 
     * @return an int representing the min ticks/quarter that would support this bar.
     */
	public int getMinTicksPerQuarter(){
		int minTicks = 1;
	    for (int i = 0; i<noteStructs.size(); i++){
		    int min = noteStructs.get(i).getMinTicks(); 
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
	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player){
		int currentTick = startTicking;
		for (int i = 0; i<noteStructs.size(); i++){
		    currentTick = noteStructs.get(i).addToPlayer(currentTick,ticksPerQuarter, player);
		}
		return currentTick;
	}
	
	/**
	 * Produces a string representation of the bar.
	 * @return: A string representing the bar.
	 */
	
	@Override
	public String toString(){
	    String output = "\n-------BAR-------";
	    for (int i = 0; i<noteStructs.size(); i++){
	        output = output + "\n" + noteStructs.get(i).toString();
	    }
	    return output;
	}
    
}
	