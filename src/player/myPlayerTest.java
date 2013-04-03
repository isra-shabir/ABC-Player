package player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.SequencePlayer;

import org.junit.Test;


/**
 * Tests myPlayer, by testing its ability to play notes, as well as account for different
 * accidentals, octaves, and key signatures. 
 * @author AHaidar
 * @category no_didit
 */
public class myPlayerTest {
    
    /**
     * Test all base notes, with accidentals
     */
	@Test
    public void testPlayer(){
	    //Test that myPlayer is able to successfully play all notes
        try {

            SequencePlayer sqPlayer = new SequencePlayer(140, 12);
            myPlayer Ahmed = new myPlayer("C", sqPlayer);
            
            String[] basenotes =   {"C","C","D","D","E","F","F","G","G","A","A","B"};
            int[] accidentals = {0 ,1,0 ,1,0 ,0 ,1,0 ,1,0 ,1,0 };
                        
            for (int i = 0; i<48; i++){
                
                String basenote;
                int accidental = 0;
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
    @Test
    public void testKeySignatures(){
        //Test that C-major scale sounds different (and correct) for different key signatures.
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
                    Ahmed.addNote(12*(iKey*7+i), 12, basenote, 10, octave);
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
        //Test that even with different key signatures, 
        //With all naturals, all keys sound the same. 
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
                    Ahmed.addNote(12*(iKey*7+i), 12, basenote, 0, octave);
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
     * Tests performance with different octaves
     * (Plays C,, through b'', no accidentas)
     */
    @Test
    public void testOctaves(){
        //Test that even with different key signatures, 
        //With all naturals, all keys sound the same. 
        try {

            SequencePlayer sqPlayer = new SequencePlayer(140, 12);
            myPlayer Ahmed = new myPlayer("C", sqPlayer);
            
            String[] basenotes =   {"C","D","E","F","G","A","B"};
            String[] octaves = {",,", ",", ""};
                        
            for (int iOct = 0; iOct<octaves.length; iOct++){
                for (int i = 0; i < 7; i++){
                    String octave = octaves[iOct];
                    String basenote = basenotes[i%7];
                    Ahmed.addNote(6*(iOct*7+i), 6, basenote, 0, octave);
                }                           
            }
            
            String[] basenotes2 =   {"c","d","e","f","g","a","b"};
            String[] octaves2 = {"", "'", "''"};
                        
            for (int iOct2 = 0; iOct2<octaves2.length; iOct2++){
                for (int i = 0; i < 7; i++){
                    String octave = octaves2[iOct2];
                    String basenote = basenotes2[i%7];
                    Ahmed.addNote(6*((iOct2+7)*7+i), 6, basenote, 0, octave);
                }                           
            }
            
            sqPlayer.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
       
}
