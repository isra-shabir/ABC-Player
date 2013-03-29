package player;

import java.util.ArrayList;

public class Parser {
	
	
	private ArrayList<Token> tokens;
	private int currentToken = 0;
	
	//Will create an array of BarLineObjects
    private ArrayList<BarLineObject> allObjects = new ArrayList<BarLineObject>();
	
	public Parser(ArrayList<Token> tokens){
	    this.tokens = tokens;
	}
	

	
	
	/**
	 * an iterator through the array of tokens to perform the first level parsing
	 * @return an ArrayList of BarLineObjects
	 */
	public ArrayList<BarLineObject> parse(){
	    this.currentToken = 0;
	    
	    while (this.currentToken < tokens.size()){
	        Token token = tokens.get(currentToken);
	        System.out.println("Looking at "+token);
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
	            this.allObjects.add(new VoiceIndicator(token.getValue()));
	        }
	        else {
	            System.out.println("Want to add "+token);
	            this.allObjects.add(new BarSignal(token.getType().name()));
	            this.currentToken++;
	        }
	    }
	    
	    return allObjects;
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
	   
	    while (inNote){
	    	
	    	if (this.currentToken>= tokens.size()){ //we reached the end of the array
	    		inNote = false;
	    		continue; 
	    	}
	    	
	        Token token = tokens.get(this.currentToken);
	        
	        //ACCIDENTAL
	        //can only be the before the note, otherwise, throw exception
	        if (token.isType("ACCIDENTAL")){
	            
	            if (aAccidental.isEmpty() && aOctave.isEmpty()  && aBasenote.isEmpty()){
	                aAccidental = token.getValue();
	            }else if(!aBasenote.isEmpty()){
	            	inNote = false;
	            	continue;
	            }else{
	            	throw new RuntimeException("There is an invalid accidental in index:"+this.currentToken+" in the tokens array");
	            }
	        }
	            
	        
	        //BASENOTE
	        else if (token.isType("BASENOTE")){
	            if (aOctave.isEmpty()  && aBasenote.isEmpty()){
	                aBasenote = token.getValue();
	            }
	            //If we have already filled up a note
	            else if (!aBasenote.isEmpty()){
	            	inNote = false;
	            	continue;
	            	
	            }
	            else{
	            	throw new RuntimeException("There is an invalid BASENOTE in index:"+this.currentToken+" in the tokens array");
                }
	        }
	        
	        //OCTAVE
	        else if (token.isType("OCTAVE")){
	            if (aOctave.isEmpty()){
	                aOctave = token.getValue();
	            }
	            else if (aBasenote.isEmpty()){
	            	throw new RuntimeException("There is an invalid OCTAVE in index: "+this.currentToken+" in the tokens array. Make sure your OCATVE comes after a basenote");
	            }
	            else{
	            	throw new RuntimeException("There is an invalid OCTAVE in index: "+this.currentToken+" in the tokens array. I am seeing multiple octaves within one note o.O");

	            }
	        }
        
	        //Handle numerator/denominator
	        else if (aBasenote.isEmpty() && ( token.isType("DIGIT") || token.isType("BACKSLASH"))){
	            throw new RuntimeException("Invalid Syntax: Num/Denom info before note definition. At index: "+this.currentToken+" in the tokens array");
	        }
	        
	        else if (token.isType("DIGIT")){
	            if (denomShown == false){
	            	
	                num = Integer.valueOf(token.getValue());
	            }
	            else if (denomShown == true){
	                denom = Integer.valueOf(token.getValue());
	                
	                inNote = false;
	            
	            }
	        }
	        
	        else if (token.isType("BACKSLASH")){
	            denomShown = true;
	            denom = 2;
	        }
	        
	        else {
	            inNote = false;
	            this.currentToken--;
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
