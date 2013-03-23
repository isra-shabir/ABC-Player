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
    	//test basic functionality for length 	
    	Lexer test = new Lexer("C2/3");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.BASENOTE, "C"));
    	expected.add(new Token(Type.NUMERATOR, "2"));
    	expected.add(new Token(Type.DENOMINATOR, "3"));

    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    
    @Test
    public void LexerTest3() {
    	//test basic functionality for ACCIDENTAL and octave
    	Lexer test = new Lexer("^C,");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    			
    	expected.add(new Token(Type.ACCIDENTAL, "^"));
    	expected.add(new Token(Type.BASENOTE, "C"));
    	expected.add(new Token(Type.OCTAVE, ","));

    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }

    
    @Test
    public void LexerTest4() {
    	//test basic functionality for CHORD and tuplet 	
    	Lexer test = new Lexer("[(2ab]");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    			
    	expected.add(new Token(Type.CHORDBEGIN, "["));
    	expected.add(new Token(Type.TUPLET, "(2"));
    	expected.add(new Token(Type.BASENOTE, "a"));
    	expected.add(new Token(Type.BASENOTE, "b"));
    	expected.add(new Token(Type.CHORDEND, "]"));
    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    
    @Test
    public void LexerTest5() {
    	//test basic functionality for BAR 	
    	Lexer test = new Lexer("a|b");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    			

    	expected.add(new Token(Type.BASENOTE, "a"));
    	expected.add(new Token(Type.BAR, "|"));
    	expected.add(new Token(Type.BASENOTE, "b"));
    	
    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    
    
    @Test
    public void LexerTest6() {
    	//test basic functionality for REPEAT 	
    	Lexer test = new Lexer("|:ab:|");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    			
    	expected.add(new Token(Type.REPEATBEG, "|:"));
    	expected.add(new Token(Type.BASENOTE, "a"));
    	
    	expected.add(new Token(Type.BASENOTE, "b"));
    	expected.add(new Token(Type.REPEATEND, ":|"));
    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    
    @Test
    public void LexerTest7() {
    	//test basic functionality for VOICE 	
    	Lexer test = new Lexer("V:ab");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.VOICE, "ab"));
    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    
    @Test
    public void LexerTest8() {
    	//test basic functionality for OPENBAR 	
    	Lexer test = new Lexer("[|a|]");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.OPENBAR, "[|"));
    	expected.add(new Token(Type.BASENOTE, "a"));
    	expected.add(new Token(Type.ENDBAR, "|]"));
    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    }
    
    @Test
    public void LexerTest9() {
    	//test basic functionality for NTHREPEAT 	
    	Lexer test = new Lexer("[2a");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.NTHREPEAT, "[2"));
    	expected.add(new Token(Type.BASENOTE, "a"));    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    }
    
    @Test
    public void LexerTest10() {
    	//test basic functionality for NTHREPEAT 	
    	Lexer test = new Lexer("a    b");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.BASENOTE, "a"));  
    	expected.add(new Token(Type.SPACE, "    "));    	
    	expected.add(new Token(Type.BASENOTE, "b"));    	

    	assertEquals(expected.toString(), test.lex().toString());     		
    }

}
