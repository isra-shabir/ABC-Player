package player;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.SequencePlayer;

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
        
        String music = file; 
        String keySignature = "C";
        int tempo = 120;
        ArrayList<String> voiceNames = new ArrayList<String>();
        
        //Create Lexer
        Lexer myLexer = new Lexer(music);
        //Tokenize
        ArrayList<Token> tokens = myLexer.lex();
        alprint(tokens);
        //Parse
        Parser myParser = new Parser(tokens);
        ArrayList<BarLineObject> barLineObjects = myParser.parse();
        //alprint(barLineObjects);
        //Parse 2
        Parser2 myParser2 = new Parser2(voiceNames);
        myParser2.parse(barLineObjects);
        ArrayList<Voice> voices = myParser2.getVoices();
        alprint(voices);
        Song mySong = new Song(voices);
        
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(tempo, mySong.getMinTicksPerQuarter());
            myPlayer MrAhmed = new myPlayer(keySignature, sqPlayer);
            mySong.addToPlayer(MrAhmed);
            sqPlayer.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
 
    }
    
    public static String getHeyJude(){
        return "^d [c5/2^D5/8C5/8^G5/8^G,3/8] z/8 ^G,3/8 z/8 [^D5/8C5/8^G5/8^G,3/8] |";
//                +"z/8 ^G,3/8 z/8 [^D5/8C5/8^G5/8^G,3/8] z/8 [c/2^G,3/8] z/8 |"
//                +"[^d/2^G5/8c5/8^G,3/8] z/8 [f/2^G,3/8] z/8 [^A2^A,5/8^D5/8=G,3/8] z/8 |"
//                +"^D,3/8 z/8 [^A,5/8^D5/8G,3/8] z/8 ^D,3/8 z/8 [^A,5/8^D5/8G,3/8] z/8 |"
//                +"^D,3/8 z/8 [^A/2^D5/8G,3/8] z/8 [c/2^G,3/8] z/8 |"
//                +"[^c5/8=G5/8^A5/8^A,3/8] z/8 ^D,3/8 z/8 [^g11/8^A5/8^c5/8^C3/8] z/8 |"
//                +"^D,3/8 z/8 [^A5/8^c5/8^C3/8] z/8 [^g3/8^D,3/8] z/8 |"
//               +"[=g3/8^A5/8^c5/8^C3/8] z/8 [^d3/8^D,3/8] z/8 [f3/8^G5/8^G,3/8] z/8 |"
//               +"[^d/4^G,3/8] ^c/4 [=c13/8^D5/8^G5/8^G,3/8] z/8 ^G,3/8 z/8 |"
//                +"[^D5/8^G5/8^G,3/8] z/8 ^G,3/8 z/8 [^D3/8^G3/8^G,3/8] z/8 |"
//                +"[^d3/8^G3/8c3/8^G,3/8] z/8 [f3/8^G5/8^c5/8^C3/8] z/8 [f7/8^C,3/8] z/8 |"
//                +"[^G^c^C3/8] z/8 [f3/8^C,3/8] z/8 [^a/8g/8^C3/8] z/8 [^g3/8f3/8z/4] ||";
               
    }

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
        
        play(getHeyJude());
        
        /**
        
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
 * 
 * 
 */
    }

    public static void alprint(ArrayList l){
        for (int i = 0; i<l.size(); i++){
            System.out.println(l.get(i));
        }
    }
}
