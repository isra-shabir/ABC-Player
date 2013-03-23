package player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import player.Token.Type;

/**
 * The lexer will take the input from the abc file and creates a list of tokens for the parser to use
 * @author azizkag
 *
 */
public class Lexer {

	private String stream;
	
	/**
	 * Creates the lexer over the string abc
	 * @param abc
	 */
	public Lexer(String abc){
		this.stream= abc;
	}
	
	
	/**
	 * 
	 * @return ArrayList<Token> returns an arraylist of tokens 
	 */
	public ArrayList<Token> lex(){
		
		ArrayList<Token> tokens= new ArrayList<Token>();
		
		//create a list of all the regex of interest from Token
    	StringBuffer tokenPatternsBuffer = new StringBuffer();
    	for (Type tokenType : Type.values())
            tokenPatternsBuffer.append(String.format("|(%s)", tokenType.getRegex()));
    	
    	
    	
    	//we take substring(1) to get rid of the "|" at the beginning of the string
    	Pattern patterns = Pattern.compile(tokenPatternsBuffer.substring(1));
		
    	Matcher matcher = patterns.matcher(this.stream);
    	
    	//define the different patterns from the tokens
    	Pattern var = Pattern.compile(Type.BASENOTE.getRegex());
    	Pattern num = Pattern.compile(Type.NUM.regex);
    	Pattern mul = Pattern.compile(Type.MUL.regex);
    	Pattern add = Pattern.compile(Type.ADD.regex);
    	Pattern lb = Pattern.compile(Type.LB.regex);
    	Pattern rb = Pattern.compile(Type.RB.regex);
		return tokens;
	}
}
