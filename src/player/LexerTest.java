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
    	expected.add(new Token(Type.DIGIT, "2"));
    	expected.add(new Token(Type.BACKSLASH, "/"));
    	expected.add(new Token(Type.DIGIT, "3"));

    	assertEquals(expected.toString(), test.lex().toString());     		
    
    }
    @Test
    public void LexerTest2b() {
    	//test basic functionality for length 	
    	Lexer test = new Lexer("G 2/3");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.BASENOTE, "G"));
    	expected.add(new Token(Type.SPACE, " "));
    	expected.add(new Token(Type.DIGIT, "2"));
    	expected.add(new Token(Type.BACKSLASH, "/"));
    	expected.add(new Token(Type.DIGIT, "3"));

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
    public void LexerTest7b() {
    	//test basic functionality for VOICE 	
    	// test for anything but newline?
    	Lexer test = new Lexer("V: ab 456dg ");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.VOICE, " ab 456dg"));
    	expected.add(new Token(Type.SPACE, " "));
    	
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
    	
    	expected.add(new Token(Type.SECONDREPEAT, "[2"));
    	expected.add(new Token(Type.BASENOTE, "a"));    	
    	assertEquals(expected.toString(), test.lex().toString());     		
    }
    
    @Test
    public void LexerTest10() {
    	//test basic functionality for Space 	
    	Lexer test = new Lexer("a    b");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.BASENOTE, "a"));  
    	expected.add(new Token(Type.SPACE, "    "));    	
    	expected.add(new Token(Type.BASENOTE, "b"));    	

    	assertEquals(expected.toString(), test.lex().toString());     		
    }
    
    @Test
    public void LexerTest11() {
    	//test basic functionality a collection of things (small piece) 	
    	Lexer test = new Lexer("(3c/c/c/ (3G/G/G/ (3E/E/E/ (3C/C/C/ |");
    	
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.TUPLET, "(3"));  
    	expected.add(new Token(Type.BASENOTE, "c"));    	
    	expected.add(new Token(Type.BASENOTE, "c")); 
    	expected.add(new Token(Type.BASENOTE, "c")); 
    	expected.add(new Token(Type.SPACE, " "));
    	
    	expected.add(new Token(Type.TUPLET, "(3"));  
    	expected.add(new Token(Type.BASENOTE, "G"));    	
    	expected.add(new Token(Type.BASENOTE, "G")); 
    	expected.add(new Token(Type.BASENOTE, "G"));
    	expected.add(new Token(Type.SPACE, " "));
    	
    	expected.add(new Token(Type.TUPLET, "(3"));  
    	expected.add(new Token(Type.BASENOTE, "E"));    	
    	expected.add(new Token(Type.BASENOTE, "E")); 
    	expected.add(new Token(Type.BASENOTE, "E")); 
    	expected.add(new Token(Type.SPACE, " "));
    	
    	expected.add(new Token(Type.TUPLET, "(3"));  
    	expected.add(new Token(Type.BASENOTE, "C"));    	
    	expected.add(new Token(Type.BASENOTE, "C")); 
    	expected.add(new Token(Type.BASENOTE, "C")); 
    	expected.add(new Token(Type.SPACE, " "));
    	
    	expected.add(new Token(Type.BAR, "|"));

    	assertEquals(expected.toString(), test.lex().toString());     		
    }
    
    @Test
    public void LexerTest12(){
    	//test basic functionality of composer of piece
    	Lexer test = new Lexer("C: Beethoven");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	
    	expected.add(new Token(Type.NAME, "Beethoven"));
    	 
    }
    
    
    @Test
    public void LexerTest13(){
    	//test basic functionality of title of piece
    	Lexer test = new Lexer("T: The Light");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.TITLE, "The Light"));
    	
    }
    
    @Test
    public void LexerTest14(){
    	//test basic functionality of Keysignature of piece
    	Lexer test = new Lexer("K: Am");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.KEYSIGNATURE, "Am"));
    	
    }
    
    @Test
    public void LexerTest14b(){
    	//test basic functionality of keysignature of piece
    	Lexer test = new Lexer("K: A");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.KEYSIGNATURE, "A"));
    }
    
    
    @Test
    public void LexerTest15(){
    	//test basic functionality of default length of piece
    	Lexer test = new Lexer("L: 2/3");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.DEFLENGTH, "2/3"));
    }
    
    @Test
    public void LexerTest15b(){
    	//test basic functionality of default length of piece
    	Lexer test = new Lexer("L: 2 / 3");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.DEFLENGTH, "2 / 3"));
    }
    
   
    
    public void LexerTest16(){
    	//test basic functionality of meter of piece
    	Lexer test = new Lexer("M: 2/3");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.METER, "2/3"));
    	
    }	
    
    public void LexerTest17(){
    	//test basic functionality of indexNum  of piece
    	Lexer test = new Lexer("X: 3");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.INDEXNUM, "3"));
    	
    }
    
    public void LexerTest18(){
    	//test basic functionality of Tempo of piece
    	Lexer test = new Lexer("Q: 2");
    	ArrayList<Token> expected = new ArrayList<Token>();
    	expected.add(new Token(Type.TEMPO, "2"));
    	
    }	
}
