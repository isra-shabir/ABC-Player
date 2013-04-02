package player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;


import org.junit.Test;

import sound.SequencePlayer;

public class NoteStructTest {
    
    @Test
    /**
     * Test base notes, without accidentals
     */
    public void testPlayer(){
       
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(140, 12);
            myPlayer Ahmed = new myPlayer("Cm", sqPlayer);
            
            notePrint("C", ""  , ",");
            notePrint("D", "^^", "");
            notePrint("c", ""  , "");
            notePrint("d", "_" , "");
            notePrint("d", "^" , "'");
            
            int ticksPerQuarter = 12;
            
            Note C = new Note("C", "","", 1, 8);
            
            C.addToPlayer(0, ticksPerQuarter, Ahmed);
            
            Note E = new Note("E", "","", 1, 2);
            E.addToPlayer(24, ticksPerQuarter, Ahmed);
            
            Note G = new Note("G", "","", 1, 4);
            G.addToPlayer(48, ticksPerQuarter, Ahmed);
            
            Note F = new Note("F", "","", 1, 1);
            F.addToPlayer(72, ticksPerQuarter, Ahmed);
            
            sqPlayer.play();        
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
                    
    }
    
    /**
     * Creates a note with the above arguments, and prints it
     * Using its toPrint()
     * @param basenote
     * @param accidental
     * @param octave
     * 
     */
    private void notePrint(String basenote, String accidental, String octave){
        Note theNote = new Note(basenote, accidental, octave,1,1);
        System.out.println(theNote.toString());
    }
}
