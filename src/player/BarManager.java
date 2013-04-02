package player;

import java.util.ArrayList;

import data.Note;

public class BarManager{
	
    myPlayer player;
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<Integer> tracker = new ArrayList<Integer>();
    
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
    
     
    public void addNote(int startTicking, int length, String basenote, int accidental, String octave){
        updateTracker(basenote, accidental);
        accidental = processNote(basenote, accidental);
        //Play note
        this.player.addNote(startTicking, length, basenote, accidental, octave);
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
    
    
    
}
	