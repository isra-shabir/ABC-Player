package transformers;

import static org.junit.Assert.*;

import org.junit.Test;



public class ParserTest {
	String dummyHeader = "X:0\nT:hello\nL:4/4\nQ:100\nK:c\n";
	@Test
	public void ParserTest1(){
		//test note constructor with only a note
		
		Lexer test = new Lexer(dummyHeader+"AB");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		System.out.println("tokens: "+parse.getTokensString());

		assertEquals(output,"[10A4/4, 10B4/4]");
	}
	
	@Test
	public void ParserTest2(){
		//test note constructor with notes and accidental
		
		Lexer test = new Lexer(dummyHeader+"_A ^B");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		System.out.println("tokens: "+parse.getTokensString());
		assertEquals(output,"[-1A4/4, 1B4/4]");
	}
	
	@Test
	public void ParserTest3(){
		//test note constructor with notes and lengths
		
		Lexer test = new Lexer(dummyHeader+"A2/3BC/");
		Parser parse = new Parser(test.lex());
		
		System.out.println(test.lex().toString());
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[10A8/12, 10B4/4, 10C2/4]");
	}
	
	@Test
	public void ParserTest4(){
		//test note constructor with notes and lengths (no den)
		
		Lexer test = new Lexer(dummyHeader+"_A/");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[-1A2/4]");
	}
	
	@Test
	public void ParserTest5(){
		//test note constructor with notes and lengths (no den)
		
		Lexer test = new Lexer(dummyHeader+"_A/5");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[-1A4/20]");
	}
	
	@Test
	public void ParserTest6(){
		//test note constructor with notes and octaves
		
		Lexer test = new Lexer(dummyHeader+"_A,B");
		Parser parse = new Parser(test.lex());
		
		String output = (parse.parse().toString());
		System.out.println(output);
		assertEquals(output,"[-1A,4/4, 10B4/4]");
	}
	
	@Test
    public void ParserTest7(){
        //test bar
        
        Lexer test = new Lexer(dummyHeader+"|");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[BAR]");
    }
	
	@Test
    public void ParserTest8(){
        //test end bar
        
        Lexer test = new Lexer(dummyHeader+"|]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[ENDBAR]");
    }
	
	@Test
    public void ParserTest9(){
        //test end bar
        
        Lexer test = new Lexer(dummyHeader+"^B,=B,|");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[1B,4/4, 0B,4/4, BAR]");
    }
	
	@Test
    public void ParserTest10(){
        //test chord
        
        Lexer test = new Lexer(dummyHeader+"[CEG]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[Chord: \n10C4/4\n10E4/4\n10G4/4\n]");
    }
	
	@Test
    public void ParserTest11(){
        //test chord in Bar
        
        Lexer test = new Lexer(dummyHeader+"C [CEG] |]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[10C4/4, Chord: \n10C4/4\n10E4/4\n10G4/4\n, ENDBAR]");
        
    }
	
	@Test
    public void ParserTest12(){
        //test chord with time
        
        Lexer test = new Lexer(dummyHeader+"C [C1/2E1/2G1/2] |]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[10C4/4, Chord: \n10C2/4\n10E2/4\n10G2/4\n, ENDBAR]");
        
    }
	
	@Test
    public void ParserTest13(){
        //test chord with time
        
        Lexer test = new Lexer(dummyHeader+"C [C1/2E1/2z1/2] |]");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[10C4/4, Chord: \n10C2/4\n10E2/4\n10z2/4\n, ENDBAR]");
        
    }
	
	@Test
    public void ParserTest14(){
        //test header parsing
        
        Lexer test = new Lexer("X: 0  \nT:hello");
        Parser parse = new Parser(test.lex());
        assertEquals(parse.getIndexNum(),0);
        assertEquals(parse.getTitle(),"hello");
        
    }
	
	@Test
    public void ParserTest15(){
        //test header parsing- testing Meter
        
        Lexer test = new Lexer("X: 0  \nT:hello\nQ: 100\n M:1/16");
        Parser parse = new Parser(test.lex());
        assertEquals(parse.getIndexNum(),0);
        assertEquals(parse.getTitle(),"hello");
        assertEquals(parse.getTempo(),100);
        assertEquals(parse.getMeter().toString(),"[1, 16]");
    }
	
	@Test
    public void ParserTest16(){
        //test header parsing- testing defLength
        
        Lexer test = new Lexer("X: 0  \nT:hello\nQ: 100\n L:1/16");
        Parser parse = new Parser(test.lex());
        assertEquals(parse.getIndexNum(),0);
        assertEquals(parse.getTitle(),"hello");
        assertEquals(parse.getTempo(),25);
        assertEquals(parse.getDefNum(),1);
        assertEquals(parse.getDefDen(),16);
    }
	

	@Test
    public void ParserTest17(){
        //test header parsing- testing the deletion of the tokens
        
        Lexer test = new Lexer("X: 0  \nT:hello\nQ: 100\n L:1/16\nK:C \n C C");
        Parser parse = new Parser(test.lex());
        assertEquals(parse.getIndexNum(),0);
        assertEquals(parse.getTitle(),"hello");
        assertEquals(parse.getTempo(),25);
        assertEquals(parse.getDefNum(),1);
        assertEquals(parse.getDefDen(),16);
        assertEquals(parse.getKey(),"C ");
    }	
	
	@Test
    public void ParserTest18(){
        //test tuplet - make sure the times are appropriately changed
        
        Lexer test = new Lexer(dummyHeader+"C (3C/2D/2E/2");
        Parser parse = new Parser(test.lex());
        
        String output = (parse.parse().toString());
        System.out.println(output);
        assertEquals(output,"[10C4/4, Tuplet 3\n10C4/12\n10D4/12\n10E4/12]");
        
    }
	
	
	
}


