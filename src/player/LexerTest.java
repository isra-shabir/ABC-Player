package player;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import player.Token.Type;


public class LexerTest {
	
    @Test
    public void LexerTest1() {
    	//test basic functionality for basenote 	
    	Lexer test = new Lexer("AB");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    			
    	expected.add(new Token(Type.BASENOTE, "A"));
    	expected.add(new Token(Type.BASENOTE, "B"));

    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    
    @Test
    public void LexerTest2() {
    	//test basic functionality for basenote 	
    	Lexer test = new Lexer("2/3");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    			
    	expected.add(new Token(Type.BASENOTE, "A"));
    	expected.add(new Token(Type.BASENOTE, "B"));

    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
}
