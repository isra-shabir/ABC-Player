package player;

import java.util.ArrayList;


public class Tuplet implements NoteStruct{
    
    ArrayList<Note> notes = new ArrayList<Note>();
    int numNotes = 0;
    
    int value;
    int timeNumerator;
    int timeDenominator;
    
    /**
     * A tuplet can represent
     * 1 of 3 values - 2, 3, 4
     * each of these can be created by
     * feeding specific values to the constructor
     * @param value
     */
    
    public Tuplet(int value){
        if (value == 2 || value == 3 || value == 4){
            this.value = value;
        }
        else {
            throw new IllegalArgumentException("Tuplet value is "+value+", which is Illegal. Must be 2, 3, or 4.");
        }
    }
    
    /**
     * Adds a note to the tuplet (with adjusted length). 
     * @param note - the note to be added to the tuplet. 
     * This note's time is mutated according to Tuplet rules.
     */
    public void addNote(Note note){
        note.tupletTimeMutate(this.value);
        this.notes.add(note);
        this.numNotes++;
    }
    
    /**
     * Figures out the min amount of ticks/qurter needed to represent this tuplet
     * @return the minimum number of ticks/quarter needed to represent this tuplet
     */
    public int getMinTicks() {
        return this.notes.get(0).getMinTicks();
    }
    
    /**
     * Gets the number of ticks that this tuplet will occupy
     * @param int ticksPerQuarter - the number of ticks per quarter
     * @return The number of ticks that this tuplet will occupy
     */
    public int getNumTicks(int ticksPerQuarter){
        return this.notes.get(0).getNumTicks(ticksPerQuarter);
    }
    
    /**
     * Adds this tuplet to the myPlayer object.
     * @param  
     * @param intStarting tick, the tick at which the first note in this tuplet must begin
     * @param int ticksPerQuarter - the defined number of ticks per quarter
     * @param myPlayer player - the player object which will be used.
     * 
     * @return endTick : the tick reached at the end of this tuplet.
     */
    public int addToPlayer(int startingTick, int ticksPerQuarter, BarManager barManager) {
        int currentTick = startingTick;
        for (int i = 0; i < numNotes; i++){
            //note: endTick is being set to the same variable every time this is called.
            currentTick = notes.get(i).addToPlayer(currentTick, ticksPerQuarter, barManager);
        }
        return currentTick;
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
    
    public String toString(){
        String t = "Tuplet "+this.value;
        for (int i=0; i<this.notes.size();i++){
            t = t+"\n"+this.notes.get(i).toString();
        }
        return t;
    }
    
}