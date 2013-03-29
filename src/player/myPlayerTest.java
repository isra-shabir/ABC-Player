package player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.SequencePlayer;

import org.junit.Test;

public class myPlayerTest {
    
    //@Test
    /**
     * Test base notes, without accidentals
     */
    public void testPlayer(){
        try {

            SequencePlayer sqPlayer = new SequencePlayer(140, 12);
            myPlayer Ahmed = new myPlayer("C", sqPlayer);
            
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
                                
                Ahmed.addNote(12*i, 12*(i+1), basenote, accidental, octave);
            }
            
            sqPlayer.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests alterations of key signatures
     */
    //@Test
    public void testKeySignatures(){
        try {

            String[] basenotes =   {"C","D","E","F","G","A","B"};
            String[] keys = {"C", "G", "D"};
                        
            for (int iKey = 0; iKey<keys.length; iKey++){
                SequencePlayer sqPlayer = new SequencePlayer(140, 12);
                String key = keys[iKey];
                System.out.println("Key is "+key);
                myPlayer Ahmed = new myPlayer(key, sqPlayer);
                for (int i = 0; i < 7; i++){
                    String octave = "";
                    String basenote = basenotes[i%7];
                    String accidental = "";
                    Ahmed.addNote(12*(iKey*7+i), 12, basenote, accidental, octave);
                }                           
                sqPlayer.play();
            }
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Tests performance when key signature effects are cancelled
     * (By =)
     * Should play all as C major
     */
    @Test
    public void testSilencedKeySignatures(){
        try {

            String[] basenotes =   {"C","D","E","F","G","A","B"};
            String[] keys = {"C", "G", "D"};
                        
            for (int iKey = 0; iKey<keys.length; iKey++){
                SequencePlayer sqPlayer = new SequencePlayer(140, 12);
                String key = keys[iKey];
                System.out.println("Key is "+key);
                myPlayer Ahmed = new myPlayer(key, sqPlayer);
                for (int i = 0; i < 7; i++){
                    String octave = "";
                    String basenote = basenotes[i%7];
                    String accidental = "=";
                    Ahmed.addNote(12*(iKey*7+i), 12, basenote, accidental, octave);
                }                           
                sqPlayer.play();
            }
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
       
}
