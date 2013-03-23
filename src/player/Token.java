package player;

/**
* A token is a lexical item that the parser uses.
*/
public class Token {
		
	/**
 	* All the types of tokens that can be made.
 	*/
	
	public static enum Type {
	
		BASENOTE("[C]"), ACCIDENTAL("[\\_^]"), OCTAVE("\\,"), 
		LENGTH("[1-9]?\\/[1-9]?"),CHORDBEGIN("\\["), CHORDEND("\\]"), 
		TUPLET("[\\(3[\\(2 [\\(4]]]"), BAR("\\|"), REPEATBEG("\\|:"), REPEATEND("\\:|"), 
		VOICE("[a-zA-Z]+[0-9]*[a-zA-Z]*"), ENDBAR("\\ |]"), SPACE("[ b\t\n\r\f]");
		
		private String pattern;
		
		private Type(String str){
			this.pattern = str;
			
		}		
	}
	
	private Type type;
	private String value;
	
	public Token(Type t, String v){
		
		this.type = type;
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
	
	
	
	
}
