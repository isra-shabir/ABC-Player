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
    	Pattern nume = Pattern.compile(Type.NUMERATOR.getRegex());
    	Pattern deno = Pattern.compile(Type.DENOMINATOR.getRegex());
    	Pattern note = Pattern.compile(Type.BASENOTE.getRegex());
    	Pattern acc = Pattern.compile(Type.ACCIDENTAL.getRegex());
    	Pattern octave = Pattern.compile(Type.OCTAVE.getRegex());
    	Pattern len = Pattern.compile(Type.LENGTH.getRegex());
    	Pattern choBeg = Pattern.compile(Type.CHORDBEGIN.getRegex());
    	Pattern choEnd = Pattern.compile(Type.CHORDEND.getRegex());
    	Pattern tup = Pattern.compile(Type.TUPLET.getRegex());
    	Pattern bar = Pattern.compile(Type.BAR.getRegex());
    	Pattern repeat = Pattern.compile(Type.REPEATBEG.getRegex());
    	Pattern voice = Pattern.compile(Type.VOICE.getRegex());
    	Pattern endBar = Pattern.compile(Type.ENDBAR.getRegex());
    	Pattern space = Pattern.compile(Type.SPACE.getRegex());
    	
    	
    	
    	while (matcher.find()){
    		
    		String token = matcher.group();
    		
    		if(nume.matcher(token).matches())		tokens.add(new Token(Type.NUMERATOR, token));
    		else if(deno.matcher(token).matches())		tokens.add(new Token(Type.DENOMINATOR, token));
    		else if(note.matcher(token).matches())		tokens.add(new Token(Type.BASENOTE, token));
    		else if(acc.matcher(token).matches())		tokens.add(new Token(Type.ACCIDENTAL, token));
    		else if(octave.matcher(token).matches())		tokens.add(new Token(Type.OCTAVE, token));
    		else if(len.matcher(token).matches())		tokens.add(new Token(Type.LENGTH, token));
    		else if(choBeg.matcher(token).matches())		tokens.add(new Token(Type.CHORDBEGIN, token));
    		else if(choEnd.matcher(token).matches())		tokens.add(new Token(Type.CHORDEND, token));
    		else if(tup.matcher(token).matches())		tokens.add(new Token(Type.TUPLET, token));
    		else if(bar.matcher(token).matches())		tokens.add(new Token(Type.BAR, token));
    		else if(repeat.matcher(token).matches())		tokens.add(new Token(Type.REPEATBEG, token));
    		else if(voice.matcher(token).matches())		tokens.add(new Token(Type.VOICE, token));
    		else if(endBar.matcher(token).matches())		tokens.add(new Token(Type.ENDBAR, token));
    		else if(space.matcher(token).matches())		tokens.add(new Token(Type.SPACE, token));
    	}
		return tokens;
	}
}
