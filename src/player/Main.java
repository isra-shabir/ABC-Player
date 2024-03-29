package player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import data.BarLineObject;
import data.Song;
import data.Voice;

import sound.SequencePlayer;
import transformers.Lexer;
import transformers.Parser;
import transformers.Parser2;
import transformers.Token;

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
        int lnum = 1;
        int lden = 1;
        if (myParser.getMeter().size() == 2 ){
        	lnum = myParser.getMeter().get(0);
        	lden = myParser.getMeter().get(1);
        }
        
        
        //Parse 1
        ArrayList<String> voiceNames = myParser.getVoiceNames(); //an ArrayList of the names of voices
        
        //Parse 2
        Parser2 myParser2 = new Parser2(voiceNames,lnum,lden);
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

        try {
            play(readABCFile("sample_abc\\"+"fur_elise" +
            		".abc"));
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    /**
     * 
     * @param name - name of file (w location from default)
     * @return the string in the file
     * @throws IOException if invalid file / not found
     */
    private static String readABCFile(String name) throws IOException {

        File file = new File(name);
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
