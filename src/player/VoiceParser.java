package player;

import java.util.ArrayList;

public class VoiceParser {
	
	
	private ArrayList<BarLineObject> voiceObjects = new ArrayList<BarLineObject>();
	private String voiceName;
	
	private Voice voice;
	
	public VoiceParser(String name){
	    this.voiceName = name;
	    voice = new Voice(this.voiceName);
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
	    ArrayList<NoteStruct> barFiller = new ArrayList<NoteStruct>();
	    
	    //Used solely by diff-end-repeats
	    ArrayList<Bar> repeatedSecondBars = new ArrayList<Bar>(); 
	    boolean repeatingSecond = false;
	    
	    for (int i = 0; i < numObjects; i++){
	               
	        //If a note, chord or tuplet, add to barFiller.
	        if (voiceObjects.get(i).isNotestruct()){
	            barFiller.add((NoteStruct) voiceObjects.get(i));
	        }
	        
	        //A bar signal
	        else {
	            Bar newbar = new Bar();
	            for (int i2 = 0; i2<barFiller.size(); i2++){
	                newbar.add(barFiller.get(i2));
	            }
//	            System.out.println("\n"+this.voiceName + " Parser - Adding Bar:");
//	            System.out.println(newbar.toString());
	            voice.add(newbar);
	            if (repeatingSecond){
	                repeatedSecondBars.add(newbar);
	            }
	            barFiller.clear();
	            
	            if (voiceObjects.get(i).isType("FIRSTREPEAT")){
	                
	            }
	            
	            else if (voiceObjects.get(i).isType("SECONDREPEAT")){
	                voice.repeat();
	                repeatingSecond = true;
	            }
	            
	            else if (voiceObjects.get(i).isType("REPEAT") && repeatingSecond == false){
	                voice.repeat();
	                voice.hitStart();
	            }
	            
	            else if (voiceObjects.get(i).isType("REPEAT") && repeatingSecond == true){
	                voice.replaceLast(repeatedSecondBars);
                    repeatingSecond = false;
                    repeatedSecondBars.clear();
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
