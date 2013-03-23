package player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.SequencePlayer;

import org.junit.Test;

public class myPlayerTest {
    
    @Test
    /**
     * Test base notes, without accidentals
     */
    public void testPlayer(){
        try {

            SequencePlayer sqPlayer = new SequencePlayer(140, 12);
            myPlayer Ahmed = new myPlayer("Cm", sqPlayer);
            
            String[] basenotes =   {"C","C","D","D","E","F","F","G","G","A","A","B"};
            String[] accidentals = {"" ,"^","" ,"^","" ,"" ,"^","" ,"^","" ,"^","" };
                        
            for (int i = 0; i<48; i++){
                
                String basenote;
                String accidental = "";
                String octave = "";
                basenote = basenotes[i%12];
                accidental = accidentals[i%12];
                if (i < 12){
                    octave = ",";
                }
                else if (i<24){
                    
                }
                else if (i<36){
                    basenote = basenote.toLowerCase();
                }
                else if (i<48){
                    basenote = basenote.toLowerCase();
                    octave = "'";
                }
                                
                System.out.println(accidental + basenote + octave);
                Ahmed.addNote(12*i, 12*(i+1), basenote, accidental, octave);
            }
            
            sqPlayer.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
        
    
}
