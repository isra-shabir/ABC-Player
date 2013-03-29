package player;

public class Note implements NoteStruct{
    
    private int timeNumerator;
    private int timeDenominator;
    
    private String basenote;
    private String accidental;
    private String octave;
    
    
    /**
     * Constructs Note object
     * @param aBasenote - a string indicating the basenote, such as "C", "e", or "z"
     * @param aAccidental - a string indicating the accidental, such as "^^", "_", or "="
     * @param aOctave - a string indicating the octave, such as "," or "'"
     * @param num - an int: the time numerator 
     * @param denom - an int: the time denominator
     */
    public Note(String aBasenote, String aAccidental, String aOctave, int num, int denom){
        this.basenote = aBasenote;
        this.accidental = aAccidental;
        this.octave = aOctave;
        this.timeNumerator = num;
        this.timeDenominator = denom;
        this.cleanTime();
    }
    
    /**
     * Fixes timeNumrator, timeDenominator.
     * so that timeDenominator becomes a multiple of 4.  
     * @modify modifies timeNumerator and timeDenominator
     */
    private void cleanTime(){
        
        int originalNumerator = this.timeNumerator;
        int originalDenominator = this.timeDenominator;
        while (this.timeDenominator % 4 != 0){
            this.timeDenominator += originalDenominator;
            this.timeNumerator += originalNumerator;         
        }
    }
    
    /**
     * Returns the minimum number of ticks, which is timeDenominator
     * @return Min number of ticks per quarter needed
     */
    public int getMinTicks(){
        return timeDenominator / 4;
    }
    
    /**
     * Multiplies time length by a factor according to tuplet rules
     * To be used only by tuplets.
     * @param tupletValue - a 2, 3, or 4 (duplet, triplet, quadlet)
     * @modify modifies timeNumerator, timeDenominator
     */
    public void tupletTimeMutate(int tupletValue){
        if (tupletValue == 2){
            this.timeNumerator = this.timeNumerator * 3;
            this.timeDenominator = this.timeDenominator * 2;
        } 
        else if (tupletValue == 3){
            this.timeNumerator = this.timeNumerator * 2;
            this.timeDenominator = this.timeDenominator * 3;
        } 
        else if (tupletValue == 4){
            this.timeNumerator = this.timeNumerator * 3;
            this.timeDenominator = this.timeDenominator * 4;
        } 
        
        this.cleanTime();
        
    }
    
    
    /**
     * Gets the number of ticks that this note will occupy
     * @param int ticksPerQuarter - the number of ticks per quarter
     * @return The number of ticks that this note will occupy
     */
    public int getNumTicks(int ticksPerQuarter){
        int maxDenom = 4 * ticksPerQuarter;
        int numTicks = (maxDenom / this.timeDenominator) * this.timeNumerator;
        return numTicks;
    }
    
    
    /**
     * Adds this note to the myPlayer object, returns the reached tick.
     *   
     * @param intStarting tick, the tick at which this note must begin
     * @param int ticksPerQuarter - the defined number of ticks per quarter
     * @param myPlayer player - the player object which will be used.
     * 
     * @return endTick : the tick reached at the end of this note.
     */
    public int addToPlayer(int startingTick, int ticksPerQuarter, myPlayer player){
        int length = this.getNumTicks(ticksPerQuarter);
        player.addNote(startingTick, length, this.basenote, this.accidental, this.octave);
        return startingTick + length;
    }
   
    /**
     * Gets a string interpretation of the note. 
     * @return a string representation of the note.
     */
    public String toString(){
        return accidental+" "+basenote+" "+octave+ " "+timeNumerator+"/"+timeDenominator;
    }

    public boolean isNotestruct() {
        return true;
    }
    
    public boolean isVoiceIndicator(){
        return false;
    }
    
    public boolean isType(String type) {
        return false;
    }
    
}