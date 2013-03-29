package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void ParserTest1(){
		//test note constructor with only a note
		
		Lexer test = new Lexer("AB");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[ A  4/4,  B  4/4]");
	}
	
	@Test
	public void ParserTest2(){
		//test note constructor with notes and accidental
		
		Lexer test = new Lexer("_A ^B");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A  4/4, ^ B  4/4]");
	}
	
	@Test
	public void ParserTest3(){
		//test note constructor with notes and lengths
		
		Lexer test = new Lexer("A2/3BC/");
		Parser parse = new Parser(test.lex());
		
		System.out.println(test.lex().toString());
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[ A  8/12,  B  4/4,  C  2/4]");
	}
	
	@Test
	public void ParserTest4(){
		//test note constructor with notes and lengths (no den)
		
		Lexer test = new Lexer("_A/");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A  2/4]");
	}
	
	@Test
	public void ParserTest5(){
		//test note constructor with notes and lengths (no den)
		
		Lexer test = new Lexer("_A/5");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A  4/20]");
	}
	
	@Test
	public void ParserTest6(){
		//test note constructor with notes and octaves
		
		Lexer test = new Lexer("_A,B");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[_ A , 4/4,  B  4/4]");
	}
	
	@Test
    public void ParserTest7(){
        //test bar
        
        Lexer test = new Lexer("|");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[BAR]");
    }
	
	@Test
    public void ParserTest8(){
        //test end bar
        
        Lexer test = new Lexer("|]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[ENDBAR]");
    }
	
	@Test
    public void ParserTest9(){
        //test end bar
        
        Lexer test = new Lexer("^B,=B,|");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[^ B , 4/4, = B , 4/4, BAR]");
    }
	
	@Test
    public void ParserTest10(){
        //test chord
        
        Lexer test = new Lexer("[CEG]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[Chord: \n C  4/4\n E  4/4\n G  4/4\n]");
    }
	
	@Test
    public void ParserTest11(){
        //test chord in Bar
        
        Lexer test = new Lexer("C [CEG] |]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[ C  4/4, Chord: \n C  4/4\n E  4/4\n G  4/4\n, ENDBAR]");
        
    }
	
	@Test
    public void ParserTest12(){
        //test chord with time
        
        Lexer test = new Lexer("C [C1/2E1/2G1/2] |]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[ C  4/4, Chord: \n C  2/4\n E  2/4\n G  2/4\n, ENDBAR]");
        
    }
	
	@Test
    public void ParserTest13(){
        //test chord with time
        
        Lexer test = new Lexer("C [C1/2E1/2z1/2] |]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[ C  4/4, Chord: \n C  2/4\n E  2/4\n z  2/4\n, ENDBAR]");
        
    }
	
	
	
}
