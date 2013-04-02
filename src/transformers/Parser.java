package transformers;

import java.util.ArrayList;

import data.BarLineObject;
import data.BarSignal;
import data.Chord;
import data.Lexer;
import data.Note;
import data.Tuplet;
import data.VoiceIndicator;


public class Parser {
	
	/**
	 * write some documentation
	 */
	
	
	private ArrayList<Token> tokens;
	private int currentToken = 0;
	
	
	private String title,name, key = "C";
    private int indexNum,tempo, rawTempo =0; 
    private ArrayList<String> voice = new ArrayList<String>();
    private int defLengthNum = 1;
    private int defLengthDen = 4;
    private ArrayList<Integer> meter = new ArrayList<Integer>();
    
	
	/**
	 * 
	 * @return String rep of the list of tokens.
	 */
	public String getTokensString(){
		return this.tokens.toString();
	}
	
	//Will create an array of BarLineObjects
    private ArrayList<BarLineObject> allObjects = new ArrayList<BarLineObject>();
	
	public Parser(ArrayList<Token> tokens){
	    this.tokens = tokens;
	    this.HeaderInfo();
	}
	
	/**
	 * an iterator through the array of tokens to perform the first level parsing
	 * @return an ArrayList of BarLineObjects
	 */
	public ArrayList<BarLineObject> parse(){
	    this.currentToken = 0;
	    
	    while (this.currentToken < tokens.size()){
	        Token token = tokens.get(currentToken);
//	        System.out.println("Looking at "+token);
	        if (token.isType("SPACE") || token.isType("COMMENT")){
	            this.currentToken++;
	        }
	        else if (token.isType("ACCIDENTAL") || token.isType("BASENOTE")){
                this.allObjects.add(this.noteConstructor());
            }
	        else if (token.isType("CHORDBEGIN")){
	            this.currentToken++;
	            this.allObjects.add(this.chordConstructor());
	        }
	        else if (token.isType("TUPLET")){
	            int value = Integer.parseInt(token.getValue().substring(1));
	            this.currentToken++;
	            this.allObjects.add(this.tupletConstructor(value));
	        }
	        else if (token.isType("VOICE")){
	            this.currentToken++;
	            this.allObjects.add(new VoiceIndicator(token.getValue()));
	        }
	        else {
	            if (token.getType().name() == "BAR" || token.getType().name() == "FIRSTREPEAT" ||
	                    token.getType().name() == "SECONDREPEAT" || token.getType().name() == "REPEATEND" ||
	                            token.getType().name() == "REPEATBEG" || token.getType().name() == "ENDBAR"){
	            this.allObjects.add(new BarSignal(token.getType().name()));
	            this.currentToken++;
	            }
	            else {
	                throw new IllegalArgumentException("Error in Parser: Found unexpected token: "+ token.getType().name()
	                        + "that does not belong to a Note, is not a Bar Signal or a Voice Indicator");
	            }
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
//	        System.out.println("Note token: "+token);
	        
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
	        else if (aBasenote.isEmpty() && ( token.isType("DIGIT") || token.isType("FORWARDSLASH"))){
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
	        
	        else if (token.isType("FORWARDSLASH")){
	            denomShown = true;
	            denom = 2;
	        }
	        
	        else {
	            inNote = false;
	            this.currentToken--;
	        }
	        
	        this.currentToken++;
	    }
	    	    
	    return new Note(aBasenote, accidentalToInt(aAccidental), aOctave, num * defLengthNum, denom * defLengthDen);
	}

	/**
     * Gets a the number of semitones that must be shifted from accidental
     * @param accidental - a string such as "^" or "_"
     * @return an int corresponding to the number of semitones that must be added.
     */
	private int accidentalToInt(String accidental) {
	    if (accidental.equals("")){
            return 10;
        }
        else if (accidental.equals("^^")){
            return 2;
        }
        else if (accidental.equals("^")){
            return 1;
        }
        else if (accidental.equals("=")){
            return 0;
        }
        else if (accidental.equals("_")){
            return -1;
        }
        else if (accidental.equals("__")){
            return -2;
        }
        else {
            throw new IllegalArgumentException("Invalid Accidental. Received {"+accidental+"}, an Invalid combination of ^ _ =");
        }
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
	    while (this.currentToken < tokens.size() && tokens.get(this.currentToken).isType("CHORDEND") == false){
//	        System.out.println("Chord while loop");
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
	private Tuplet tupletConstructor(int value){
	    
	    Tuplet tuplet = new Tuplet(value);
	    while (this.currentToken < tokens.size() && (tokens.get(this.currentToken).isType("BASENOTE") == true || 
	            tokens.get(this.currentToken).isType("ACCIDENTAL") == true )){
	        System.out.println("Tuplet loop: "+tokens.get(this.currentToken));
	        Note newTupletNote = this.noteConstructor();
	        System.out.println(newTupletNote);
	        tuplet.addNote(newTupletNote);
	    }
	    return tuplet;
	}
	
	
	/**
	 * This method is called by the constructor to parse the header data from the list of tokens
	 * There are a few requirements on the header:
	 *1-	The first field in the header must be the index number ('X').
	 *2-	The second field in the header must be the title ('T').
	 *3-	The last field in the header must be the key ('K').
	 *4-	Each field in the header occurs on a separate line.
	 * @modify: it will delete all the header tokens from tokens
	 */
	private void HeaderInfo(){
		
	    ArrayList<Integer> defLength = new ArrayList<Integer>();
		//checking requirements. First is indexNum second is title
		if (!tokens.get(0).isType("INDEXNUM")){
			throw new RuntimeException("first field in header must be index num");
		}else if (!tokens.get(2).isType("TITLE")){
			throw new RuntimeException("second field in header must be title");
		}
		
		int lastInd=0;
		for (int i=0; i< this.tokens.size(); i++){
			Token current=this.tokens.get(i);
			if (current.isType("INDEXNUM"))				this.indexNum=Integer.valueOf(current.getValue().replace(" ",""));
			else if (current.isType("TITLE"))			this.title=current.getValue();
			else if (current.isType("NAME"))			this.name=current.getValue();
			else if (current.isType("DEFLENGTH")){
				String text = current.getValue();
				Lexer small = new Lexer(text);
				
				for (Token token: small.lex()){
					if (token.isType("DIGIT"))			defLength.add(Integer.valueOf(token.getValue().replace(" ", "")));
				}
				this.defLengthNum = defLength.get(0);
				this.defLengthDen = defLength.get(1);
				this.tempo = (this.rawTempo * 4 * this.defLengthNum) / this.defLengthDen;
            }
			
			else if (current.isType("METER")){
				String text = current.getValue();
				Lexer small = new Lexer(text);
				for (Token token: small.lex()){
					if (token.isType("DIGIT"))			this.meter.add(Integer.valueOf(token.getValue().replace(" ", "")));
				}
			}
			else if (current.isType("TEMPO")){
			    this.rawTempo=Integer.valueOf(current.getValue().replace(" ", ""));
			    this.tempo = (this.rawTempo * 4 * this.defLengthNum) / this.defLengthDen;
			}
			else if (current.isType("VOICE"))			this.voice.add(current.getValue());
			else if (current.isType("KEYSIGNATURE")){
				//terminate the loop
				this.key=current.getValue();
				lastInd = i; 
				break;
			}

		}
		
		//delete the header tokens
		for (int i=lastInd; i>=0;i--){
			this.tokens.remove(i);
		}		
	}
	
	
	/**
	 * A set of get methods to get all the header info from the parser
	 */
	public int getIndexNum(){
		return this.indexNum;
	}
	public String getTitle(){
		return this.title;
	}
	public String getName(){
		return this.name;
	}
	public ArrayList<Integer> getMeter(){
		return this.meter;
	}
	public int getTempo(){
		return this.tempo;
	}
	public String getKey(){
		return this.key;
	}
	public ArrayList<String> getVoiceNames(){
		return this.voice;
	}

    public Object getDefNum() {
        return this.defLengthNum;
    }
    public Object getDefDen() {
        return this.defLengthDen;
    }
}



