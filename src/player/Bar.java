package player;

import java.util.ArrayList;

public class Bar implements BarStruct {
	
    ArrayList<NoteStruct> noteStructs = new ArrayList<NoteStruct>();
    int numStructs = 0;
    
	/**
	 * 
	 */

    public void add(NoteStruct struct){
        this.noteStructs.add(struct);
        numStructs++;
    }
    
    /**
     * Returns the minimum number of Ticks per Quarter needed to display all the notes in the Bar. 
     * This is the maximum of the minTicksPerBar for each struct. 
     */
	public int getMinTicksPerQuarter(){
		int minTicks = 1;
	    for (int i = 0; i<numStructs; i++){
		    int min = noteStructs.get(i).getMinTicks(); 
		    if (min > minTicks){
		        minTicks = min;
		    }
		}
	    return minTicks;
	}
	
	/**
	 * Calls addToPlayer on all the structs in its list
	 * Updates the tick, and returns its value at the end.
	 */

	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player){
		int currentTick = startTicking;
		for (int i = 0; i<numStructs; i++){
		    currentTick = noteStructs.get(i).addToPlayer(currentTick,ticksPerQuarter, player);
		}
		return currentTick;
	}
    
}
	