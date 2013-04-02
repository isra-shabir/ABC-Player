package data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import transformers.Token;
import transformers.Token.Type;

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

    	Pattern C = Pattern.compile(Type.C.getRegex());
    	Pattern digit = Pattern.compile(Type.DIGIT.getRegex());
    	Pattern forwardSlash = Pattern.compile(Type.FORWARDSLASH.getRegex());
    	Pattern note = Pattern.compile(Type.BASENOTE.getRegex());
    	Pattern acc = Pattern.compile(Type.ACCIDENTAL.getRegex());
    	Pattern octave = Pattern.compile(Type.OCTAVE.getRegex());
    	Pattern choBeg = Pattern.compile(Type.CHORDBEGIN.getRegex());
    	Pattern choEnd = Pattern.compile(Type.CHORDEND.getRegex());
    	Pattern tup = Pattern.compile(Type.TUPLET.getRegex());
    	Pattern bar = Pattern.compile(Type.BAR.getRegex());
    	Pattern repeatBeg = Pattern.compile(Type.REPEATBEG.getRegex());
    	Pattern repeatEnd = Pattern.compile(Type.REPEATEND.getRegex());
    	Pattern voice = Pattern.compile(Type.VOICE.getRegex());
    	Pattern openBar = Pattern.compile(Type.OPENBAR.getRegex());
    	Pattern endBar = Pattern.compile(Type.ENDBAR.getRegex());
    	Pattern firstRepeat = Pattern.compile(Type.FIRSTREPEAT.getRegex());
    	Pattern secondRepeat = Pattern.compile(Type.SECONDREPEAT.getRegex());
    	Pattern comment = Pattern.compile(Type.COMMENT.getRegex());
    	Pattern space = Pattern.compile(Type.SPACE.getRegex());
    	

    	Pattern name = Pattern.compile(Type.NAME.getRegex());
    	Pattern keySignature = Pattern.compile(Type.KEYSIGNATURE.getRegex());
    	Pattern defLength = Pattern.compile(Type.DEFLENGTH.getRegex());
    	Pattern meter = Pattern.compile(Type.METER.getRegex());
    	Pattern tempo = Pattern.compile(Type.TEMPO.getRegex());
    	Pattern title = Pattern.compile(Type.TITLE.getRegex());
    	Pattern indexNum = Pattern.compile(Type.INDEXNUM.getRegex());

    	Pattern invalid = Pattern.compile(Type.INVALID.getRegex());

    	
    	while (matcher.find()){
    		
    		String token = matcher.group();

    		//we will use the capturing groups in our pattern to 

    		if(matcher.group(1) != null)					tokens.add(new Token(Type.NAME, token));
    		else if(matcher.group(2) != null)				tokens.add(new Token(Type.KEYSIGNATURE, token));
    		else if(matcher.group(3) != null)				tokens.add(new Token(Type.DEFLENGTH, token));
    		else if(matcher.group(4) != null)				tokens.add(new Token(Type.METER, token));
    		else if(matcher.group(5) != null)				tokens.add(new Token(Type.TEMPO, token));
    		else if(matcher.group(6) != null)				tokens.add(new Token(Type.TITLE, token));
    		else if(matcher.group(7) != null)				tokens.add(new Token(Type.INDEXNUM, token));
    		else if(matcher.group(8) != null)				tokens.add(new Token(Type.VOICE, token));
 

    		else if(C.matcher(token).matches())				continue;
    		else if(note.matcher(token).matches())			tokens.add(new Token(Type.BASENOTE, token));
    		else if(forwardSlash.matcher(token).matches())		tokens.add(new Token(Type.FORWARDSLASH, token));
    		else if(digit.matcher(token).matches())			tokens.add(new Token(Type.DIGIT, token));
    		else if(acc.matcher(token).matches())			tokens.add(new Token(Type.ACCIDENTAL, token));
    		else if(octave.matcher(token).matches())		tokens.add(new Token(Type.OCTAVE, token));
    		else if(choBeg.matcher(token).matches())		tokens.add(new Token(Type.CHORDBEGIN, token));
    		else if(choEnd.matcher(token).matches())		tokens.add(new Token(Type.CHORDEND, token));
    		else if(tup.matcher(token).matches())			tokens.add(new Token(Type.TUPLET, token));
    		else if(repeatBeg.matcher(token).matches())		tokens.add(new Token(Type.REPEATBEG, token));
    		else if(repeatEnd.matcher(token).matches())		tokens.add(new Token(Type.REPEATEND, token));
    		else if(bar.matcher(token).matches())			tokens.add(new Token(Type.BAR, token));
    		else if(openBar.matcher(token).matches())		tokens.add(new Token(Type.OPENBAR, token));
    		else if(endBar.matcher(token).matches())		tokens.add(new Token(Type.ENDBAR, token));
    		else if(firstRepeat.matcher(token).matches())	tokens.add(new Token(Type.FIRSTREPEAT, token));
    		else if(secondRepeat.matcher(token).matches())	tokens.add(new Token(Type.SECONDREPEAT, token));
    		else if(comment.matcher(token).matches())       tokens.add(new Token(Type.COMMENT, token));
    		else if(space.matcher(token).matches())			tokens.add(new Token(Type.SPACE, token)); 	
    		else if(invalid.matcher(token).matches()){
    			throw new RuntimeException("There is an invalid entry "+token+"in your abc file. YOU STUPID?");
    		}
    	}
		return tokens;
	}
}
