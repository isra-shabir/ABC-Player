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
        
        String music = "";
        String keySignature = "";
        int tempo = 120;
        ArrayList<String> voiceNames = new ArrayList<String>();
        
        //Create Lexer
        Lexer myLexer = new Lexer(music);
        //Tokenize
        ArrayList<Token> tokens = myLexer.lex();
        //Parse
        Parser myParser = new Parser(tokens);
        ArrayList<BarLineObject> barLineObjects = myParser.parse();
        //Parse 2
        Parser2 myParser2 = new Parser2(voiceNames);
        myParser2.parse(barLineObjects);
        ArrayList<Voice> voices = myParser2.getVoices();
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
