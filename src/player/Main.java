package player;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

//import sound.SequencePlayer;

/**
 * Main entry point of your application.
 */
public class Main {

    /**
     * Plays the input file using Java MIDI API and displays
     * header information to the standard output stream.
     * 
     * (Your code should not exit the application abnormally using
     * System.exit().)
     * 
     * @param file the name of input abc file
     */
    public static void play(String file) {
        // YOUR CODE HERE
 
    }

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
        
//        SequencePlayer sp = new SequencePlayer(100,4);
        
//        int start = 0;
//        int len = 4;
//        int[] array = {2, 2, 1, 2, 2, 2, 1};
//        
//        
//        for (int i = 60; i < 62; i++){
//            sp.addNote(i, start, start+len);
//            start = start + len;
//        }
           
//        int len = 4; 
//        for (int i = 0; i < 10; i++){
//            sp.addNote(12*i, i*len, i*len+1);
//        }
        
//        int current = 0;
//        for (int i = 0; i < 14; i++){
//            int len = 6;
//            if (i>=6){
//                len = 3;
//            }
//            
//            sp.addNote(30 + 4*(i%2), current, current + len);
//            current = current + len;
//        }
//        
//        sp.play();
//        
//        System.out.println(sp.toString());
//         //CALL play() HERE
    }

}
