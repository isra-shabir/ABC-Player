package player;

import org.junit.Test;
import static org.junit.Assert.*;


public class TokenTest {

    @Test
    public void TokenTest1() {
    	//test basic functionality for the toString() method	
    	Token test = new Token(Token.Type.BASENOTE, "C");
    	
    	String output = test.toString();

    	assertEquals("(<BASENOTE> C)",output);
        		
    
    }
}
