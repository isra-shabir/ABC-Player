package player;

import java.util.ArrayList;

import data.Note;

public class BarManager{
	
    myPlayer player;
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<Integer> tracker = new ArrayList<Integer>();
    double totalLength;
    String sBar = "";
    
    /**
     * Creates a bar manager, with a myPlayer object that should be used
     * to add notes to.
     * @param player
     */
    public BarManager(myPlayer player){
        this.player = player;
        tracker.add(10);
        tracker.add(10);
        tracker.add(10);
        tracker.add(10);
        tracker.add(10);
        tracker.add(10);
        tracker.add(10);
    }
    
    /**
     * Adds a note to the player, but first changes accidentals if any previous
     * notes of the same basenote had any (gives that to them, but only if it has nothing).
     * @param startTicking - tick where this note should start
     * @param length - length of this note
     * @param basenote
     * @param accidental - an int, such as -1 or 10
     *          10 indicates no accidental. 0 indicates natural.
     *          1,2,-1,-2 are sharp(s), flat(s)
     * @param octave - a string, such as ',' or ','
     * @param num numerator of the note's time length
     * @param den denominator of the note's time length
     */
    public void addNote(int startTicking, int length, String basenote, int accidental, String octave, int num, int den){
        updateTracker(basenote, accidental);
        accidental = processNote(basenote, accidental);
        totalLength += ((double) num / den);
        //Play note
        this.player.addNote(startTicking, length, basenote, accidental, octave);
        this.sBar = this.sBar + accidental+basenote+octave+"--";
    }
    
    /**
     * Changes note's accidental to that defined by tracker.
     * @param note - note to be changed, based on tracker
     * @return note after change
     */
	private int processNote(String basenote, int accidental) {
	    String base = basenote.toLowerCase();
	    	    
	    if (accidental != 10){
	        //No need to change anything - this has its own accidental
	        return accidental;
	    }
	    
	    //Note has no accidental: must be processed. 
	    if (base.equals("z")){
            
        }
	    else if (base.equals("a")){
            return tracker.get(0);
        }
	    else if (base.equals("b")){
            return tracker.get(1);
        }
	    else if (base.equals("c")){
            return tracker.get(2);
        }
	    else if (base.equals("d")){
            return tracker.get(3);
        }
	    else if (base.equals("e")){
            return tracker.get(4);
        }
	    else if (base.equals("f")){
            return tracker.get(5);
        }
	    else if (base.equals("g")){
            return tracker.get(6);
        }
	    else {
	        throw new IllegalArgumentException("Invalid Basenote!? - "+basenote);
	    }
        return 10; //Never happens!!
    }

	/**
	 * Updates tracker, if note has an accidental
	 * @param basenote - basenote of the note
	 * @param accidental - accidental of the note
	 */
    private void updateTracker(String basenote, int accidental) {
        String base = basenote.toLowerCase();
        
        if (accidental == 10){
            //This is raw. 
            return;
        }
        
        if (base.equals("z")){
            
        }
        else if (base.equals("a")){
            tracker.set(0, accidental);
        }
        else if (base.equals("b")){
            tracker.set(1, accidental);
        }
        else if (base.equals("c")){
            tracker.set(2, accidental);
        }
        else if (base.equals("d")){
            tracker.set(3, accidental);
        }
        else if (base.equals("e")){
            tracker.set(4, accidental);
        }
        else if (base.equals("f")){
            tracker.set(5, accidental);
        }
        else if (base.equals("g")){
            tracker.set(6, accidental);
        }
    }
    
    /**
     * Issues a warning if the bar is not of proper length
     * @param lnum - the L numerator from the header
     * @param lden - the L denominator from the header
     */
    public void checkTotalLength(int lnum, int lden){
    	double l = (double) lnum / lden;
    	if (this.totalLength != l && totalLength != 0) {
    		System.err.println("Warning: The sum of the length of notes in one of your Bars is not equal to the value specified in the header file (or default value)"
    				+"\nlnum: "+lnum+" / lden: "+lden + " != total Length: "+ totalLength +"\n" + this.sBar);
    	}
    }
    
}
	