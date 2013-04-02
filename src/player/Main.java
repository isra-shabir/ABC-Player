package player;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import data.BarLineObject;
import data.Lexer;
import data.Song;
import data.Voice;

import sound.SequencePlayer;
import transformers.Parser;
import transformers.Parser2;
import transformers.Token;

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
        
        System.out.println("Playing...");
        String music = file; 

        //Create Lexer
        Lexer myLexer = new Lexer(music);
        //Tokenize
        ArrayList<Token> tokens = myLexer.lex();        
        //Parse
        Parser myParser = new Parser(tokens);
        ArrayList<BarLineObject> barLineObjects = myParser.parse(); 
        //Get Header Vals from the parser
        String keySignature = myParser.getKey() ;
        int tempo =   myParser.getTempo() ;
        
        //Parse 1
        ArrayList<String> voiceNames = myParser.getVoiceNames(); //an ArrayList of the names of voices
        
        //Parse 2
        Parser2 myParser2 = new Parser2(voiceNames);
        myParser2.parse(barLineObjects);
        ArrayList<Voice> voices = myParser2.getVoices();
        
        Song mySong = new Song(voices);
        SequencePlayer sqPlayer;
        try {
            System.out.println("Creating SQ "+tempo+", minTpQ: "+mySong.getMinTicksPerQuarter());
            sqPlayer = new SequencePlayer(tempo, mySong.getMinTicksPerQuarter());
            System.out.println("Playing in "+keySignature);
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
    
    public static String getAwayFromTheSun(){
        
            return "^c3/8 ^g3/8 f3/8 ^c3/8 ^g3/8 f3/8 ^c3/8 ^g3/8 =c3/8 ^g3/8 f3/8 c3/8 |"
                    +"=g3/8 ^g3/8 ^d3/8 ^g3/8 ^G3/8 ^g3/8 ^d3/8 ^G3/8 ^g3/8 ^d3/8 ^G3/8 |"
                    +"^g3/8 =G3/8 ^g3/8 ^d3/8 G3/8 =g3/8 ^g3/8 ^a3/8 c'3/8 [^c3/8f3/4^g3/8] |"
                    +"[^g3/8z3/8] [f3/8z3/8] [^c3/8z3/8] [^g3/8z3/8] [f3/8z3/8] [^c3/8z3/8] |"
                    +"^g3/8 [=c9/8^g3/8f3/4] [^g3/2z3/8] [f15/8z3/8] [c15/8z3/8] =g3/8 |"
                    +"[^g3/4z3/8] ^d3/8 ^g3/8 [^D3/4^A6G6^d6=g15/8z3/8] ^a3/8 [^D3/4z3/8] |"
                    +"^g3/8 [^D9/8z3/8] [=g3z3/4] ^D3/8 [^D3/4z3/8] ^a3/8 [^D3/4z3/8] ^g3/8 |"
                    +"[^D3/2z3/8] [=g9/8z3/4] ^g3/8 [^c3/8^C3] ^g3/8 f3/8 ^c3/8 ^g3/8 f3/8 |";
               
    }
    
    public static String getFallenLeaves(){
        
        return "[e/4^c/4] z/4 [e/4^G/4] z/4 [e/4^c/4] z/4 [e/4^G/4] z/4 [^d/4=c/4] z/4 |"
               +" [^d/4^G/4] z/4 [^d/4c/4] z/4 [^d/4^G/4] z/4 [^d/4c/4] z/4 [^d/4^G/4] |"
                 +"       z/4 [^f/4^G/4] z/4 [^f/4^G/4] z/4 [e/4B/4] z/4 [e/4^G/4] z/4 [e/4B/4] |"
                   +"     z/4 [e/8^G/8] z/4 [e/4A/4] z/4 [e/4^G/4] z/4 [e/4A/4] z/4 [e/4^G/4] |"
                     +"   z/4 [^c/4^G/4^F/4] z3/8 [^c/4^G/4^F/4] z3/8 [^c/4^G/8^F/4] z3/8 |"
                       +" [^c/4^G/8^F/4] z3/8 [^c/4^G/8E/4] z3/8 [^c/4^G/8E/4] z/4 [^c^G/8E] |"
                        +"z3/8 ^G/8 z3/8 [=c/4^G/4^D/4] z/4 [c/4^G/4^D/4] z/4 [c/4^G/4^D/4] z/4 |"
                        +"[^c/8=c/4^G/4^D/4] z/8 ^d/8 z/8 [e/4^c/4] z/4 [^c/4e/4^G/4] z/4 |"
                        +"[e/4^c/4] z/4 [^f/4e/4^G/4] z/8 [^g^d/4=c/4] z/4 [^d/4^G/4] z/4 |"
                        +"[^g/4^d/4c/4] z/4 [^g/4^d/4^G/4] z/4 [^g/4^d/4c/4] z/4 [a/4^d/4^G/4]"
                        +"z/4 [^g/4^f/4^G/4] z/4 [^f/4^G/4] z/4 [e/2B/4] z/4 [e/2^G/4] z/4"
                        +"[e/8B/8] z/4 [e/4^G/4] z/4 [e/2A/4] z/4 [e/2^G/4] z/4 [e/4A/4] z/4"
                        +"[e/4^G/4] z/4 [e^c/4^G/8^F/4] z3/8 [^c/4^G/8^F/4] z3/8"
                        +"[e/4^c/4^G/8^F/4] z3/8 [e/4^c/4^G/8^F/4] z3/8 [e7/8^c/8^G/8E/8] z/4"
                        +"[^c/4^G/8E/4] z3/8 [e/4^c^G/8E] z3/8 [^f/4^G/8] z3/8"
                        +"[^d2=c/4^G/4^D/4] z/4 [c/4^G/4^D/4] z/4 [c/4^G/4^D/4] z/4"
                        +"[c/4^G/4^D/4] z/4 [^c^G^C^C,] |";
           
}

    public static String getFurElise(){
        return "e^d|e^deB=dc|A2 z CEA|B2 z E^GB|c2 z Ee^d|";
    }
       
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {

        try {
            play(readABCFile("pluginbaby" +
            		".abc"));
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    private static String readABCFile(String name) throws IOException {

        File file = new File("sample_abc\\"+name);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while(scanner.hasNextLine()) {        
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
