package player;

import java.util.ArrayList;

public class Parser {
	
	
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private int numVoices;
	private int currentToken = 0;
    private ArrayList<String> voiceNames;
	
	public Parser(){
	    
	}
	

	
	
	
	public void parse(ArrayList<BarLineObject> barStuff){
	    this.currentToken = 0;
	}	
	
	public Note noteConstructer(){
	    String aBasenote = "";
	    String aAccidental = "";
	    String aOctave = "";
	    int num = 1;
	    int denom = 1;
	    
	    boolean inNote = true;
	    boolean denomShown = false;
	    
	    while (inNote == true){
	        Token token = tokens.get(this.currentToken);
	        
	        //ACCIDENTAL
	        if (token.Type == ACCIDENTAL){
	            
	            if (aAccidental.isEmpty() && aOctave.isEmpty()  && aBasenote.isEmpty()){
	                aAccidental = token.toString();
	            }
	            
	            //If we have already filled up a note...
	            else if (aBasenote.isEmpty() == false){
	                break;
	            }
	        }
	            
	        //BASENOTE
	        else if (token.getType() == BASENOTE){
	            if (aOctave.isEmpty()  && aBasenote.isEmpty()){
	                aBasenote = token.toString();
	            }
	            
	            //If we have already filled up a note
	            else if (aBasenote.isEmpty() == false){
                    break;
                }
	        }
	        
	        //OCTAVE
	        else if (token.Type == OCTAVE){
	            if (aOctave.isEmpty()){
	                aOctave = token.toString();
	            }
	        }
        
	        //Handle numerator/denominator
	        else if (aBasenote.isEmpty() == true && ( token.Type = number || token.Type = DENOMTHING)){
	            System.out.println("Invalid Syntax: Num/Denom info before note definition.");
	        }
	        
	        else if (token.Type = number){
	            if (denomShown == false){
	                num = Integer.valueOf(token.toString());
	            }
	            else if (denomShown == true){
	                denom = Integer.valueOf(token.toString());
	                inNote = false;
	            }
	        }
	        
	        else if (token.Type == DENOMTHING){
	            denomShown = true;
	            denom = 2;
	        }
	        
	        else {
	            isNote = false;
	        }
	        
	        this.currentToken++;
	    }
	    
	    return new Note(aBasenote, aAccidental, aOctave, num, denom);
	}
	
}
