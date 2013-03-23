package player;

import sound.Pitch;
import sound.SequencePlayer;

public class myPlayer {
    
    String keySignature;
    SequencePlayer sqPlayer;
    
    /**
     * Constructs the player, through which all nodes are added to the sequence player
     * @param keySig - the key signature. A string, such as "Cm"
     * @param sequencePlayer = the sequence player that notes should be added to. 
     */
    public myPlayer(String keySig, SequencePlayer sequencePlayer){
        this.keySignature = keySig;
        this.sqPlayer = sequencePlayer;
    }
    
    /**
     * Add a note to the sequence player
     * @param startingTick - the tick at which the note begins
     * @param length - the number of ticks that this note will occupy
     * @param basenote - the string correponding the matching middle white note 
     * such as "C" or "d"
     * @param accidental - a string identifying semitone shifts, such as "^"
     * @param octave - a string identifying semitone shifts, such as ","
     */
    public void addNote(int startingTick, int length, String basenote,
            String accidental, String octave) {
        
        int accidentalNum = numerateAccidental(accidental);
        int octaveNum = numerateOctave(octave, basenote);
        
        String upperBasenote = basenote.toUpperCase();
        char pitch = upperBasenote.charAt(0);
                
        sqPlayer.addNote(new Pitch(pitch).transpose(Pitch.OCTAVE * octaveNum).accidentalTranspose(accidentalNum).toMidiNote(),  startingTick, length);
                
    }
    
    /**
     * Gets a the number of semitones that must be shifted from accidental
     * @param accidental - a string such as "^" or "_"
     * @return an int corresponding to the number of semitones that must be added.
     */
    private int numerateAccidental(String accidental){
        if (accidental == "^^"){
            return 2;
        }
        else if (accidental == "^"){
            return 1;
        }
        else if (accidental == "="){
            return 0;
        }
        else if (accidental == "_"){
            return -1;
        }
        else if (accidental == "__"){
            return -2;
        }
        return 0;
    }
    
    /**
     * Gets a the number of octaves that must be shifted from middle keys
     * @param octave - a string such as "," or "'" that indicates octave shifts
     * @return an int corresponding to the number of octaves that must be added.
     */
    private int numerateOctave(String octave, String basenote){
                
        if (octave == "'"){
            return 2; }
        else if (octave == "''"){
            return 3; }
        else if (octave == "'''"){
            return 4; }
        
        else if (octave == ","){
            return -1; }
        else if (octave == ",,"){
            return -2; }
        else if (octave == ",,,"){
            return -3; }
        
        String lowerBasenote = basenote.toLowerCase();
        if (lowerBasenote == basenote){
            return 1;
        }
        
        return 0;

    }
    
}
