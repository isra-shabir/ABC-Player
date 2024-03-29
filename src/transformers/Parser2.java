package transformers;

import java.util.ArrayList;

import data.BarLineObject;
import data.Voice;
import data.VoiceIndicator;

import player.VoiceParser;

public class Parser2 {
	
	/**
	 * The Job of this parser is as follows:
	 * It takes in a list of Notes, Chords, Tuplets, BarLineObjects and VoiceIndicator. 
	 * 
	 * 
	 * the raw tokens.
	 * It returns a list with the following changes:
	 *     Note, Chord, and Tuplet objects now replace all basenote, accidental, and octave tokens. 
	 *     
	 * All other tokens left the same. 
	 */
	private ArrayList<VoiceParser> voiceParsers;
	private int numVoices;
	private int currentVoiceParser = 0;
    private ArrayList<String> voiceNames;
    private int lnum, lden;
    
    /**
     * 
     * @param voiceNames
     */
	
	public Parser2(ArrayList<String> voiceNames, int lnum, int lden){
	    
		this.lnum = lnum;
		this.lden = lden;
	    this.numVoices = voiceNames.size();
	    this.voiceNames = voiceNames;
	    
	    voiceParsers = new ArrayList<VoiceParser>();
	    
	    if (this.numVoices > 0){
    	    for (int i = 0; i < this.numVoices; i++){
    	        voiceParsers.add(new VoiceParser(voiceNames.get(i), this.lnum, this.lden));
    	    }
	    }
	    else {
	        this.numVoices = 1;
	        this.voiceNames.add("Fairouz");
	        voiceParsers.add(new VoiceParser("Fairouz",this.lnum, this.lden));
	    }
	}
	
	/**
	 * Changes the current voice based on a new voice name.
	 * @param name - the name of the voice that should now be used.
	 * @modify this.currentVoice
	 */
	private void changeVoice(String name){
	    
	    for (int i = 0; i<this.numVoices; i++){
	        if (name.equals(voiceNames.get(i))){
	            this.currentVoiceParser = i;
	            return;
	        }
	    }
	    throw new IllegalArgumentException("Voice name <"+name+"> not in list of voices.");
	}
	

	
	public void parse(ArrayList<BarLineObject> barObjects){
	    
	    //Go through things, and add to appropriate voice parser.
	    for (int i = 0; i < barObjects.size(); i++){
	        if (barObjects.get(i).isVoiceIndicator()){
	            this.changeVoice(((VoiceIndicator) barObjects.get(i)).getVoiceName());
	        }
	        
	        else {
//	            System.out.println("Adding to voice of "+this.voiceNames.get(this.currentVoiceParser)+":");
//	            System.out.println(barObjects.get(i));
	            this.voiceParsers.get(this.currentVoiceParser).addToken(barObjects.get(i));
//	            System.out.println(voiceParsers.get(this.currentVoiceParser).parse());
	        }
	    }
	}
	
	/**
	 * Gets a list of voices.
	 * @return an ArrayList of Voice objects. 
	 */
	public ArrayList<Voice> getVoices(){
	    ArrayList<Voice> voices = new ArrayList<Voice>();
	    for (int i = 0; i<this.numVoices; i++){
	        voices.add(voiceParsers.get(i).parse());
	    }
	    return voices;
	}
}
