package sound;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

/**
 * Tests the playing of a ABC files. 
 * @category no_didit
 */
public class SequencePlayerTest {

    @Test
    public void warmupPiece1Test(){
        
        SequencePlayer player;
        try {

            player = new SequencePlayer(140, 12);
            
            int[] lengths = {12,12,9,3,12,9,3,9,3,24,4,4,4,4,4,4,4,4,4,4,4,4,9,3,9,3,24};
            char[] names = {'C','C','C','D','E','E','D','E','F','G'   ,'C','C','C','G','G','G','E','E','E','C','C','C','G','F','E','D','C'};
            int[] octaves = {0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            
            int counter = 0;
            for (int i = 0; i < lengths.length; i++){
                player.addNote(new Pitch(names[i]).transpose(Pitch.OCTAVE * octaves[i]).toMidiNote(),  counter,  lengths[i]);
                counter += lengths[i];
            }
            
            player.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void warmupPiece2Test(){
        
        SequencePlayer player;
        try {

            player = new SequencePlayer(200, 12);
            
            char[] names1 =    {'F','F','Z','F','Z','F','F',   'G','Z','G','Z',  'C','G','Z','E',   'E','A','B','B','A',   'G','E','G','A','F','G',   'Z','E','C','D','B','Z'};
            int[] lengths1 =   {6,6,6,6,6,6,12,                 12,12,12,12,      18,6,12,12,        6,12,12,6,12,          8,8,8,12,6,6,               6,12,6,6,9,9};
            int[] octaves1 =   {0,0,0,0,0,0,0,                  0,0,0,0,           1,0,0,0,          0,0,0,0,0,             0,1,1,1,1,1,                0,1,1,1,0,0 };
            int[] accidentals1={1,1,0,1,0,1,1,                  0,0,0,0,           0,0,0,0,          0,0,0,-1,0,            0,0,0,0,0,0,                0,0,0,0,0,0 };            
            
            int counter = 0;
            for (int i = 0; i < lengths1.length; i++){
                if (names1[i] != 'Z'){
                    player.addNote(new Pitch(names1[i]).transpose(Pitch.OCTAVE * octaves1[i]).accidentalTranspose(accidentals1[i]).toMidiNote(),  counter,  lengths1[i]);
                }
                counter += lengths1[i];
            }
            
            
            char[] names2 =    {'E','E','Z','E','Z','C','E',   'G'};
            int[] lengths2 =   {6,6,6,6,6,6,12,                12};
            int[] octaves2 =   {1,1,0,1,0,1,1,                  1};
            int[] accidentals2={0,0,0,0,0,0,0,                  0};            
            
            int counter2 = 0;
            for (int i = 0; i < lengths2.length; i++){
                if (names2[i] != 'Z'){
                    player.addNote(new Pitch(names2[i]).transpose(Pitch.OCTAVE * octaves2[i]).accidentalTranspose(accidentals2[i]).toMidiNote(),  counter2,  lengths2[i]);
                }
                counter2 += lengths1[i];
            }
            
            player.addNote(new Pitch('B').toMidiNote(),  48,  12);
            
            player.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }


}
