package player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ParserTest {

	@Test
	public void ParserTest1(){
		//test note constructor with only a note
		
		Lexer test = new Lexer("AB");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[ A ,  B ]");
	}
	
	@Test
	public void ParserTest2(){
		//test note constructor with notes and accidental
		
		Lexer test = new Lexer("_A ^B");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A , ^ B ]");
	}
	
	@Test
	public void ParserTest3(){
		//test note constructor with notes and lengths
		
		Lexer test = new Lexer("_A2/3");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A , ^ B ]");
	}
	
	@Test
	public void ParserTest4(){
		//test note constructor with notes and lengths (no den)
		
		Lexer test = new Lexer("_A/");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A , ^ B ]");
	}
	
	@Test
	public void ParserTest5(){
		//test note constructor with notes and lengths (no den)
		
		Lexer test = new Lexer("_A/5");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A , ^ B ]");
	}
	
	@Test
	public void ParserTest6(){
		//test note constructor with notes and octaves
		
		Lexer test = new Lexer("_A,B");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A ,,  B ]");
	}
	
	
}
