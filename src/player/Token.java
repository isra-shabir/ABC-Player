package player;

/**
* A token is a lexical item that the parser uses.
*/
public class Token {
		
	/**
 	* All the types of tokens that can be made.
 	*/
	
	public static enum Type {
	
		NUMERATOR("[A-Ga-g,'[b\t\n\r\f]]+[0-9]+)"),
		DENOMINATOR("\\/[0-9]"),
		BASENOTE("[A-Ga-g]"),
		ACCIDENTAL("([\\_^])|([_]+)"),
		OCTAVE("\\,"), 
		LENGTH("[0-9]?\\/[0-9]?"),
		CHORDBEGIN("\\["),
		CHORDEND("\\]"),
		TUPLET("[\\(3[\\(2 [\\(4]]]"),
		BAR("\\|"),
		REPEATBEG("\\|:"),
		REPEATEND("\\:|"), 
		VOICE("[0-9]*[a-zA-Z]+[0-9]*[a-zA-Z]*[0-9]*"),
		ENDBAR("\\ |]"),
		SPACE("[ b\t\n\r\f]");
		
		//define Type.regex so we can access the regex for each type
		private String regex;
		
		private Type(String str){
			this.regex= str;
			
		}		
		
		/**
		 * public method to retrieve Type's 
		 * regex pattern given by a string
		 * @return regex 
		 */
		
		public String getRegex(){
			return this.regex;
		}
	}
	
	private Type type;
	private String value;
	
	/**
	 * Initialize a Token
	 * @param t Type of the token
	 * @param v String value of token
	 */
	public Token(Type t, String v){
		
		this.type = t;
		this.value = v;		
	}

	/**
	 * method returns the Type of token
	 * @return token type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return string value of Token
	 */
	public String getValue() {
		return value;
	}
	
    /**
     * When we print out an ArrayList of Tokens we want it to be readable
     * This method overrides toString() with the string representation of our tokens
     */
    @Override 
    public String toString() {
    	return String.format("(<%s> %s)", this.getType(), value);
    }
}
