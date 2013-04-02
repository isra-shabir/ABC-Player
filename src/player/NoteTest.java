package player;

import org.junit.Test;
import static org.junit.Assert.*;


public class NoteTest {

    @Test
    public void NoteTest1() {
    	//test basic functionality of time representation	
    	Note test = new Note("C", "", "", 4, 8);
    	
    	String output = test.toString();
    	assertEquals(" C  2/4",output);
        		
    
    }

}
