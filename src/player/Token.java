package player;

/**
* A token is a lexical item that the parser uses.
*/
public class Token {
		
	/**
 	* All the types of tokens that can be made.
 	*/
	
	public static enum Type {
		//we need more complex lexer tests
		
		NUMERATOR("(?<=[a-gA-G ])[0-9]+"), //what should be considered a nume? (any number after BASENOTE or SPACE AND followed by?)
		DENOMINATOR("(?<=\\/)[0-9]+"), //are spaces allowed after "/" ?
		VOICE("(?<=V:)[ a-zA-z0-9]+"), //3
		NTHREPEAT("(\\[1)|(\\[2)"), //4,5,6
		BASENOTE("[A-Ga-g]"), //7
		ACCIDENTAL("[\\__\\^\\_\\=\\^^]"),
		OCTAVE("[\\,\\']+"), 
		OPENBAR("\\[\\|"),
		ENDBAR("(\\|\\|)|(\\|\\])"),
		CHORDBEGIN("\\["),
		CHORDEND("\\]"),
		TUPLET("\\([0-9]+"),
		REPEATBEG("\\|:"),
		REPEATEND(":\\|"),
		BAR("\\|"),
		SPACE("[\\s]+"); // all white spaces including newline
		
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
	
	public boolean isType(String typeName){
	    return typeName == this.type.toString();
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
