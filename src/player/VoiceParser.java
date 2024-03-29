package player;

import java.util.ArrayList;

import data.Bar;
import data.BarLineObject;
import data.NoteStruct;
import data.Voice;

public class VoiceParser {
	
	private ArrayList<BarLineObject> voiceObjects = new ArrayList<BarLineObject>();
	private String voiceName;
	
	private Voice voice;
	private int lnum, lden;
	
	/**
	 * Builds the parser
	 * @param name, string, of voice. 
	 * @param lnum : int, default bar label numerator
	 * @param lden : int, default bar label denominator
	 */
	public VoiceParser(String name, int lnum, int lden){
	    this.voiceName = name;
	    voice = new Voice(this.voiceName);
	    this.lnum = lnum;
	    this.lden = lden;
	}
	
	/**
	 * Adds a token to the VoiceParser's array of tokens.
	 * @param token 
	 */
	public void addToken(BarLineObject object){
	    voiceObjects.add(object);
	}
	
	
	/**
	 * Creates and returns the Voice after its list of objects is created
	 * @return Voice - a Voice object representing the entire voice.
	 */
	public Voice parse(){
	    int numObjects = voiceObjects.size();
	    
	    //Arrays used to construct bars...
	    ArrayList<NoteStruct> barFiller = new ArrayList<NoteStruct>();
	    
	    //Used solely by diff-end-repeats
	    ArrayList<Bar> repeatedSecondBars = new ArrayList<Bar>(); 
	    boolean repeatingSecond = false;
        boolean repeatingFirst = false;

	    int part1counter = 0;
	    int part2counter = 0;
	    
	    for (int i = 0; i < numObjects; i++){
	               
	        //If a note, chord or tuplet, add to barFiller.
	        if (voiceObjects.get(i).isNotestruct()){
	            //Check for accidentals
	            barFiller.add((NoteStruct) voiceObjects.get(i));
	        }
	        
	        //A bar signal
	        else {
	            
	            Bar newbar = new Bar(this.lnum, this.lden);
	            for (int i2 = 0; i2<barFiller.size(); i2++){
	                newbar.add(barFiller.get(i2));
	            }
	            
	            if (repeatingFirst){
	                part1counter++;
	            }
	            
	            if (repeatingSecond){
	                repeatedSecondBars.add(newbar);
	                part2counter++;
	                
	                //If its time to add the second part
	                if (part2counter == part1counter){
	                    voice.replaceLast(repeatedSecondBars);
	                    repeatedSecondBars.clear();
	                    voice.hitStart();
	                    part1counter = 0;
	                    part2counter = 0;
	                    repeatingSecond = false;
	                }
	            }
	            else {
	                voice.add(newbar);
	            }
	            
	            barFiller.clear();
	            
	            if (voiceObjects.get(i).isType("FIRSTREPEAT")){
	                if (repeatingFirst == false){
	                    repeatingFirst = true;
	                }
	                else {
	                    throw new RuntimeException("Invalid First Repeat Placement");
	                }
	            }
	            
	            else if (voiceObjects.get(i).isType("SECONDREPEAT")){
	                if (repeatingSecond == false){
	                    repeatingFirst = false;
	                    repeatingSecond = true;
                    }
                    else {
                        throw new RuntimeException("Invalid Second Repeat Placement: \n"+voiceObjects.get(i));
                    }
	            }
	            
	            else if (voiceObjects.get(i).isType("REPEATEND")){
	                voice.repeat();
	                voice.hitStart();
	            }
	            
	            else if (voiceObjects.get(i).isType("ENDBAR") || voiceObjects.get(i).isType("REPEATBEG")){
	                voice.hitStart();
	            }
	            
	        }
	        
	    }
	    
	    return this.voice;
	}
	
	
}
