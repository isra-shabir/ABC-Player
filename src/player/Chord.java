package player;
import java.util.ArrayList;

public class Chord implements NoteStruct{
    
    ArrayList<Note> notes = new ArrayList<Note>();
    
    /**
     * Adds a note to the chord. 
     * @param note - the note to be added to the Chord
     */
    public void addNote(Note note){
        this.notes.add(note);
    }
    
    /**
     * Figures out the min amount of ticks/qurter needed to represent this chord
     * @return the minimum number of ticks/quarter needed to represent this chord
     */
    public int getMinTicks() {
        return this.notes.get(0).getMinTicks();
    }
    
    /**
     * Gets the number of ticks that this chord will occupy
     * @param int ticksPerQuarter - the number of ticks per quarter
     * @return The number of ticks that this chord will occupy
     */
    public int getNumTicks(int ticksPerQuarter){
        return this.notes.get(0).getNumTicks(ticksPerQuarter);
    }
    
    /**
     * Adds this chord to the myPlayer object, returns the reached tick.
     *   
     * @param intStarting tick, the tick at which all notes in this chord must begin
     * @param int ticksPerQuarter - the defined number of ticks per quarter
     * @param myPlayer player - the player object which will be used.
     * 
     * @return endTick : the tick reached at the end of this chord.
     */
    public int addToPlayer(int startingTick, int ticksPerQuarter, myPlayer player) {
        int endTick = 0;
        for (int i = 0; i < notes.size(); i++){
            //note: endTick is being set to the same variable every time this is called.
            endTick = notes.get(i).addToPlayer(startingTick, ticksPerQuarter, player);
        }
        
        return endTick;
    }
    
    public boolean isNotestruct() {
        return true;
    }

    public boolean isType(String type) {
        return false;
    }
    
    public boolean isVoiceIndicator(){
        return false;
    }
    
}