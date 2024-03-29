package data;

import player.BarManager;

public class Note implements NoteStruct{
	
	/**
	 * This class represents stores all information about 
	 * notes (the fundamental atom used to play music) 
	 * The class also contains methods applicable to notes
	 */
    
    private int timeNumerator;
    private int timeDenominator;
    
    private String basenote;
    private int accidental;
    private String octave;
    
    
    /**
     * Constructs Note object
     * @param aBasenote - a string indicating the basenote, such as "C", "e", or "z"
     * @param aAccidental - an int indicating the accidental, "^^" = 2, "^" = 1, "=" = 0, "_" = -1, "__" = -2, 
     * 						no accidental = 0
     * @param aOctave - a string indicating the octave, such as "," or "'"
     * @param num - an int: the time numerator 
     * @param denom - an int: the time denominator
     */
    public Note(String aBasenote, int aAccidental, String aOctave, int num, int denom){
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
        
        if (this.timeDenominator > this.timeNumerator){
            int gcd = this.getGCD(this.timeDenominator, this.timeNumerator);
            this.timeDenominator = this.timeDenominator / gcd;
            this.timeNumerator = this.timeNumerator / gcd;
        }
        
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
    public int addToPlayer(int startingTick, int ticksPerQuarter, BarManager barManager){
        int length = this.getNumTicks(ticksPerQuarter);
        
        barManager.addNote(startingTick, length, this.basenote, this.accidental, this.octave, this.timeNumerator, this.timeDenominator);
        return startingTick + length;
    }
   
    /**
     * Gets a string interpretation of the note. 
     * @return a string representation of the note.
     */
    @Override
    public String toString(){
        return accidental+basenote+octave+timeNumerator+"/"+timeDenominator;
    }

    
    // collection of methods that determine what kind of object note is
    public boolean isNotestruct() {
        return true;
    }
    
    public boolean isVoiceIndicator(){
        return false;
    }
    
    public boolean isType(String type) {
        return false;
    }
    
    // collection of getter methods for note class attributes
    
    public String getBasenote(){
        return this.basenote;
    }
    
    public int getAccidental(){
        return this.accidental;
    }
    
    /**
     * Gets the greatest common denominator of a and b. 
     * @param a, an int greater than 0, and a must be larger than b
     * @param b, an int greater than 0
     * 
     * @return (int) greatest common denominator. 
     */
    private int getGCD(int a, int b){
        if (b==0){
            return a;
        }
        return getGCD(b, a%b);
    }

    /**
     * setter method for Accidentals of note objects
     * @param accidental
     */
    
    public void setAccidental(Integer accidental) {
        this.accidental = accidental;
    }
    
}