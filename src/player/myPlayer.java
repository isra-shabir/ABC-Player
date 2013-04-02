package player;

import java.util.ArrayList;

import sound.Pitch;
import sound.SequencePlayer;

public class myPlayer {
    
    String keySignature;
    SequencePlayer sqPlayer;
    
    ArrayList<String> sharps = new ArrayList<String>();
    ArrayList<String> flats = new ArrayList<String>();
    
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
    
    /**
     * Constructs sharps and flats, based on this.keySignature
     * @modify sharps, flats
     */
    private void implementKey() {
        if (this.keySignature.equals("C")){
        }
        else if (this.keySignature.equals("G") || this.keySignature.equals("E"+"m")){
            sharps.add("f");
        }
        else if (this.keySignature.equals("D") || this.keySignature.equals("B"+"m")){
            sharps.add("f");
            sharps.add("c");
        }
        else if (this.keySignature.equals("A") || this.keySignature.equals("^F"+"m")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
        }
        else if (this.keySignature.equals("E") || this.keySignature.equals("^C"+"m")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
        }
        else if (this.keySignature.equals("B") || this.keySignature.equals("^G"+"m")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
        }
        else if (this.keySignature.equals("^F") || this.keySignature.equals("^D"+"m")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
            sharps.add("e");
        }
        else if (this.keySignature.equals("^C") || this.keySignature.equals("^A"+"m")){
            sharps.add("f");
            sharps.add("c");
            sharps.add("g");
            sharps.add("d");
            sharps.add("a");
            sharps.add("e");
            sharps.add("b");
        }
        
        
        else if (this.keySignature.equals("F") || this.keySignature.equals("D"+"m")){
            flats.add("b");
        }
        else if (this.keySignature.equals("_B") || this.keySignature.equals("G"+"m")){
            flats.add("b");
            flats.add("e");
        }
        else if (this.keySignature.equals("_E") || this.keySignature.equals("C"+"m")){
            flats.add("b");
            flats.add("e");
            flats.add("a");
        }
        else if (this.keySignature.equals("_A") || this.keySignature.equals("F"+"m")){
            flats.add("b");
            flats.add("e");
            flats.add("a");
            flats.add("d");
        }
        else if (this.keySignature.equals("_D") || this.keySignature.equals("_B"+"m")){
            flats.add("b");
            flats.add("e");
            flats.add("a");
            flats.add("d");
            flats.add("g");
        }
        else if (this.keySignature.equals("_G") || this.keySignature.equals("_E"+"m")){
            flats.add("b");
            flats.add("e");
            flats.add("a");
            flats.add("d");
            flats.add("g");
            flats.add("c");
        }
        else if (this.keySignature.equals("_C") || this.keySignature.equals("_A"+"m")){
            flats.add("b");
            flats.add("e");
            flats.add("a");
            flats.add("d");
            flats.add("g");
            flats.add("c");
            flats.add("f");
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
        
        System.out.println("Adding "+basenote+" of length "+length +" at "+startingTick);
        
        if (basenote.equals("z") || basenote.equals("Z")){
            return;
        }
        
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
    
    /**
     * Returns the effect of key signature on a basenote
     * Assumes no accidentals
     * @param basenote
     * @return 0, -1, or +1 denoting accidental effect
     */
    private int keyMutate(String basenote) {
        String base = basenote.toLowerCase();
        for (int i = 0; i < sharps.size(); i++){
            if (sharps.get(i).equals(base)){
                return 1;
            }
        }
        for (int i = 0; i < flats.size(); i++){
            if (flats.get(i).equals(base)){
                return -1;
            }
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
