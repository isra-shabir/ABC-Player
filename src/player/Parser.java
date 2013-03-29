package player;

import java.util.ArrayList;

public class Parser {
	
	
	private ArrayList<Token> tokens;
	private int numVoices;
	private int currentToken = 0;
	
	//Will create an array of BarLineObjects
    private ArrayList<BarLineObject> allObjects = new ArrayList<BarLineObject>();
	
	public Parser(ArrayList<Token> tokens){
	    this.tokens = tokens;
	}
	

	
	
	
	public void parse(ArrayList<BarLineObject> barStuff){
	    this.currentToken = 0;
	    
	    while (numVoices < tokens.size()){
	        Token token = tokens.get(currentToken);
	        if (token.isType("SPACE")){
	            this.currentToken++;
	        }
	        else if (token.isType("ACCIDENTAL") || token.isType("BASENOTE")){
                this.allObjects.add(this.noteConstructor());
            }
	        else if (token.isType("CHORDBEGIN")){
	            this.currentToken++;
	            this.allObjects.add(this.chordConstructor());
	        }
	        else if (token.isType("TUPLETBEGIN")){
	            this.currentToken++;
	            this.allObjects.add(this.tupletConstructor());
	        }
	        else if (token.isType("VOICE")){
	            this.currentToken++;
	            this.allObjects.add(new VoiceIndicator(" "));
	        }
	        else {
	            this.allObjects.add(new BarSignal(token.getType().toString()));
	            this.currentToken++;
	        }
	    }
	}	
	
	/**
     * Constructs and returns a note object that has been reached
     * 
     * Requires: Current this.currentToken is at the beginning of a note (accidental or basenote)
     * @return a note object.
     * @modify this.currentToken, which is updated to the first index after the note
     */
	private Note noteConstructor(){
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
	        if (token.isType("ACCIDENTAL")){
	            
	            if (aAccidental.isEmpty() && aOctave.isEmpty()  && aBasenote.isEmpty()){
	                aAccidental = token.toString();
	            }
	            
	            //If we have already filled up a note...
	            else if (aBasenote.isEmpty() == false){
	                break;
	            }
	        }
	            
	        //BASENOTE
	        else if (token.isType("BASENOTE")){
	            if (aOctave.isEmpty()  && aBasenote.isEmpty()){
	                aBasenote = token.toString();
	            }
	            
	            //If we have already filled up a note
	            else if (aBasenote.isEmpty() == false){
                    break;
                }
	        }
	        
	        //OCTAVE
	        else if (token.isType("OCTAVE")){
	            if (aOctave.isEmpty()){
	                aOctave = token.toString();
	            }
	        }
        
	        //Handle numerator/denominator
	        else if (aBasenote.isEmpty() == true && ( token.isType("NUMBER") || token.isType("DENOMTHING"))){
	            System.out.println("Invalid Syntax: Num/Denom info before note definition.");
	        }
	        
	        else if (token.isType("NUMBER")){
	            if (denomShown == false){
	                num = Integer.valueOf(token.toString());
	            }
	            else if (denomShown == true){
	                denom = Integer.valueOf(token.toString());
	                inNote = false;
	            }
	        }
	        
	        else if (token.isType("DENOMTHING")){
	            denomShown = true;
	            denom = 2;
	        }
	        
	        else {
	            inNote = false;
	        }
	        
	        this.currentToken++;
	    }
	    
	    return new Note(aBasenote, aAccidental, aOctave, num, denom);
	}

	/**
     * Constructs and returns a chord object that has been reached
     * 
     * Requires: Current this.currentToken is at the beginning of a note (accidental or basenote)
     * @return a Chord object.
     * @modify this.currentToken, which is updated to the first index after the chord
     */
	private Chord chordConstructor(){
	    Chord chord = new Chord();
	    while (tokens.get(this.currentToken).isType("CHORDEND") == false){
	        chord.addNote(this.noteConstructor());
	    }
	    this.currentToken++;
	    return chord;
	}
	
	/**
	 * Constructs and returns a tuplet object that has been reached
	 * 
     * Requires: Current this.currentToken is at the beginning of a note (accidental or basenote)
     * @return a Tuplet object.
	 * @modify this.currentToken, which is updated to the first index after the tuplet
	 */
	private Tuplet tupletConstructor(){
	    int value = Integer.parseInt(tokens.get(this.currentToken).toString());
	    this.currentToken++;
	    Tuplet tuplet = new Tuplet(value);
	    while (tokens.get(this.currentToken).isType("SPACE") == false){
	        tuplet.addNote(this.noteConstructor());
	    }
	    return tuplet;
	}
	
}
