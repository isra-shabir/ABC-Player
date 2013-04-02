package player;

import org.junit.Test;
import static org.junit.Assert.*;


public class TokenTest {
	
	/**
	 * This class makes new tokens
	 * and tests if they are made correctly
	 * using the toString() method and comparing
	 * with string representation of known tokens
	 */

    @Test
    public void TokenTest1() {
    	//test basic functionality for the toString() method	
    	Token test = new Token(Token.Type.BASENOTE, "C");
    	
    	String output = test.toString();

    	assertEquals("(<BASENOTE> C)",output);		
    }
    
    @Test
    public void TokenTest2() {
    	//test basic functionality for the isType() method	
    	Token test = new Token(Token.Type.BASENOTE, "C");
    	
    	assertEquals(true,test.isType("BASENOTE"));
    }
}
