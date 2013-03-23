package player;

import java.util.ArrayList;


public class Tuplet implements NoteStruct{
    
    ArrayList<Note> notes = new ArrayList<Note>();
    int numNotes = 0;
    
    int value;
    int timeNumerator;
    int timeDenominator;
    
    public Tuplet(int value){
        this.value = value;
    }
    
    public void addNote(Note note){
        this.notes.add(note);
        this.numNotes++;
    }
    
    public int getDenom() {
        return this.notes.get(1).getDenom();
    }
    
    /**
     * 
     */
    public int getNumTicks(int ticksPerQuarter){
        return this.notes.get(0).getNumTicks(ticksPerQuarter);
    }
    
    /**
     * Adds this chord to the myPlayer object.
     * @param  
     * @param intStarting tick, the tick at which all notes in this chord must begin
     * @param int length - the length of this chord
     * @param myPlayer player - the player object which will be used.
     * 
     * @return endTick : the tick reached at the end of this chord.
     */
    public int addToPlayer(int startingTick, int ticksPerQuarter, myPlayer player) {
        int currentTick = startingTick;
        for (int i = 0; i < numNotes; i++){
            //note: endTick is being set to the same variable every time this is called.
            currentTick = notes.get(i).addToPlayer(currentTick, ticksPerQuarter, player);
        }
        
        return currentTick;
    }
    
}