package player;

import java.util.ArrayList;

import sound.Pitch;
import sound.SequencePlayer;

public class myPlayer {
    
    String keySignature;
    SequencePlayer sqPlayer;
    
    ArrayList<String> sharps = new ArrayList<String>();
    
    /**
     * Constructs the player, through which all nodes are added to the sequence player
     * @param keySig - the key signature. A string, such as "Cm"
     * @param sequencePlayer = the sequence player that notes should be added to. 
     */
    public myPlayer(String keySig, SequencePlayer sequencePlayer){
        this.keySignature = keySig;
        this.sqPlayer = sequencePlayer;
        this.implementKey();
    }
    
    private void implementKey() {
        if (this.keySignature.equals("C")){
        }
        else if (this.keySignature.equals("G")){
            sharps.add("f");
        }
        else if (this.keySignature.equals("D")){
            sharps.add("f");
            sharps.add("c");
        }
        else if (this.keySignature.equals("A")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
        }
        else if (this.keySignature.equals("E")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
        }
        else if (this.keySignature.equals("B")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
        }
        else if (this.keySignature.equals("^F")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
            sharps.add("e");
        }
        else if (this.keySignature.equals("^C")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
            sharps.add("e");
            sharps.add("b");
        }
        
        
        else if (this.keySignature.equals("F")){
            sharps.add("f");
        }
        else if (this.keySignature.equals("_B")){
            sharps.add("f");
            sharps.add("c");
        }
        else if (this.keySignature.equals("A")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
        }
        else if (this.keySignature.equals("E")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
        }
        else if (this.keySignature.equals("B")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
        }
        else if (this.keySignature.equals("^F")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
            sharps.add("e");
        }
        else if (this.keySignature.equals("^C")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
            sharps.add("e");
            sharps.add("b");
        }
        
        
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
        
        int accidentalNum = numerateAccidental(accidental, basenote);
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
    private int numerateAccidental(String accidental, String basenote){
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
        //Account for key signatures
        //If there is no accidental:
        else {
            return keyMutate(basenote);
        }
    }
    
    private int keyMutate(String basenote) {
        String base = basenote.toLowerCase();
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
