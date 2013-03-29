package player;

import java.util.ArrayList;

public class VoiceParser {
	
	
	private ArrayList<Token> voiceTokens = new ArrayList<Token>();
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
	public void addToken(Token token){
	    voiceTokens.add(token);
	}
	
	
	/**
	 * Creates and returns the Voice after its list of objects is created
	 * @return Voice - a Voice object representing the entire voice.
	 */
	public Voice parse(){
	    int numTokens = voiceTokens.size();
	    ArrayList<NoteStruct> barFiller = new ArrayList<NoteStruct>();
	    
	    ArrayList<Bar> repeatedSecondBars = new ArrayList<Bar>(); //Used solely by diff-end-repeats
	    boolean repeatingSecond = false;
	    
	    for (int i = 0; i < numTokens; i++){
	        
	        
	        //If a note, chord or tuplet, add to barFiller.
	        if (Token.Type == NOTESTRUCT){
	            barFiller.add(voiceTokens.get(i));
	        }
	        
	        else {
	            voice.add(new Bar(barFiller));
	            if (repeatingSecond){
	                repeatedSecondBars.add(new Bar(barFiller));
	            }
	            barFiller.clear();
	            
	            if (type = repeat1start){
	                
	            }
	            
	            else if (type = repeat2start){
	                voice.repeat();
	                repeatingSecond = true;
	            }
	            
	            else if (type == repeat && repeatSecond == false){
	                voice.repeat();
	                voice.hitStart();
	            }
	            
	            else if (type == repeat && repeatSecond == true){
	                voice.replaceLast(repeatedSecondBars);
                    repeatingSecond = false;
                    repeatedSecondBars.clear();
                    voice.hitStart();
	            }
	            
	            else if (type == endSection){
	                voice.hitStart();
	            }
	            
	        }
	        
	    }
	}
	
	
}
