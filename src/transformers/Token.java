package transformers;

/**
* A token is a lexical item that the parser uses.
*/
public class Token {
		
	/**
 	* All the types of tokens that can be made.
 	*/
	
	public static enum Type {

		NAME("(?<=C:)[^\n\r\f]+"),
		KEYSIGNATURE("(?<=K:)[^\n\r\f]+"),
		DEFLENGTH("(?<=L:)[^\n\r\f]+"),
		METER("(?<=M:)[^\n\r\f]+"),
		TEMPO("(?<=Q:)[^\n\r\f]+"),
		TITLE("(?<=T:)[^\n\r\f]+"),
		INDEXNUM("(?<=X:)[^\n\r\f]+"), //10
		VOICE("(?<=V:)[^\n\r\f]+"), //11


		C("C:"),
		DIGIT("[0-9]+"),
		FORWARDSLASH("\\/"),
		FIRSTREPEAT("(\\[1)"), //1st and 2nd
		SECONDREPEAT("(\\[2)"),
		BASENOTE("[A-Ga-gz]"), 
		ACCIDENTAL("[\\^_=]+"), 
		OCTAVE("[\\,\\']+"), 
		OPENBAR("\\[\\|"),
		ENDBAR("(\\|\\|)|(\\|\\])"),
		CHORDBEGIN("\\["),
		CHORDEND("\\]"),
		TUPLET("\\(2|\\(3|\\(4"),
		REPEATBEG("\\|:"),
		REPEATEND(":\\|"),
		BAR("\\|"),
		COMMENT("%(.)*(\\\n|\\\r|\\\f)"),
		SPACE("[\\s]+"), // all white spaces including newline
		
		INVALID("[^(T:)(C:)(Q:)(X:)(L:)(M:)(K:)(V:)]");
	
		
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
	 * This method 
	 * checks a Token's Type
	 * @param typeName
	 * @return boolean
	 */
	
	public boolean isType(String typeName){
	    return typeName == this.type.name();
	}

	/**
	 * method returns the Type of token
	 * (BASENOTE, DIGIT, OCTAVE etc)
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
