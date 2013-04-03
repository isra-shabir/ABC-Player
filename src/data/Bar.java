package data;

import java.util.ArrayList;

import player.BarManager;
import player.myPlayer;

/**
 * The Bar class is a representation of notes 
 * in batches
 * This class contains all the methods that 
 * are applicable to these batches
 * 
 * @author ishabir
 *
 */
public class Bar{
	
    ArrayList<NoteStruct> noteStructs = new ArrayList<NoteStruct>();
    
    //numerator length and denominator length
    private int lnum, lden;
    
    /**
     * Constructor for Bar. Sets the lnum and lden to the default 1 and4
     */
    public Bar(){
    	this.lnum = 1;
    	this.lden = 1;
    }
    /**
     * Constructor for Bar with lnum and lden as parameters
     * @param lnum
     * @param lden
     */
    public Bar(int lnum, int lden){
    	this.lnum = lnum;
    	this.lden= lden;
    }
    
    /**
     * this method adds noteStruct objects
     * to the noteStructs array
     * @param struct
     */
    
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
		    minTicks = lowestCommonMultiple(minTicks, min);
		}
	    System.out.println("---mtpq of bar: "+minTicks);
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
	public int addToPlayer(int startTicking, int ticksPerQuarter, myPlayer player){
		BarManager barManager = new BarManager(player);
	    int currentTick = startTicking;
		for (int i = 0; i<noteStructs.size(); i++){
		    currentTick = noteStructs.get(i).addToPlayer(currentTick,ticksPerQuarter, barManager);
		}
		barManager.checkTotalLength(this.lnum, this.lden);
		return currentTick;
	}
	
	/**
	 * Produces a string representation of the bar.
	 * @return: A string representing the bar.
	 */
	
	/**
	 * this method represents the noteStructs
	 * array as a String.
	 * Each noteStruct object is printed on a newline
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
	