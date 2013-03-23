package player;

import sound.Pitch;
import sound.SequencePlayer;

public class myPlayer {
    
    String keySignature;
    SequencePlayer sqPlayer;
    
    public myPlayer(String keySig, SequencePlayer sq){
        this.keySignature = keySig;
        this.sqPlayer = sq;
    }

    public void addNote(int startingTick, int length, String basenote,
            String accidental, String octave) {
        
        int accidentalNum = numerateAccidental(accidental);
        int octaveNum = numerateOctave(octave, basenote);
        
        String upperBasenote = basenote.toUpperCase();
        char pitch = upperBasenote.charAt(0);
        
        System.out.println("Pitch: " + pitch + ", octave: " + octaveNum +"  accidentals: " + accidentalNum);
        
        sqPlayer.addNote(new Pitch(pitch).transpose(Pitch.OCTAVE * octaveNum).accidentalTranspose(accidentalNum).toMidiNote(),  startingTick, length);
        
        // TODO Auto-generated method stub
        
    }
    
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
