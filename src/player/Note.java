package player;

public class Note implements NoteStruct{
    
    private int timeNumerator;
    private int timeDenominator;
    
    private String basenote;
    private String accidental;
    private String octave;
    
    //Constructs Note Object
    //Fills in timeNumerator, Denominator
    public Note(String aBasenote, String aAccidental, String aOctave, int num, int denom){
        this.basenote = aBasenote;
        this.accidental = aAccidental;
        this.octave = aOctave;
        this.timeNumerator = num;
        this.timeDenominator = denom;
        
    }
    
    /**
     * Returns timeNumrator, timeDenominator to simplest form. 
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
     * Returns the timeDenominator
     */
    public int getMinTicks(){
        return timeDenominator;
    }
    
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
        
        //Return things to simplest form
        this.cleanTime();
        
    }
    /**
     * @param int ticksPerQuarters - the number of ticks that should be used to represent one quarter
     *  
     */
    public int getNumTicks(int ticksPerQuarter){
        int maxDenom = 4 * ticksPerQuarter;
        int numTicks = (maxDenom / this.timeDenominator) * this.timeNumerator;
        return numTicks;
    }
    
    /**
     * @param myPlayer player - an instance of myPlayer that should receive the note.
     * @return the new tick counter
     * 
     * Adds the note to the myPlayer object. 
     */
    public int addToPlayer(int startingTick, int ticksPerQuarter, myPlayer player){
        int length = this.getNumTicks(ticksPerQuarter);
        player.addNote(startingTick, length, this.basenote, this.accidental, this.octave);
        return startingTick + length;
    }
    
    public String toString(){
        return octave+" "+basenote+" "+accidental;
    }
    
}