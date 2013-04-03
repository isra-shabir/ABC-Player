package data;

import org.junit.Test;
import static org.junit.Assert.*;


public class NoteTest {

    //Test representation of time in simplest form, 
    //Maintaining a denominator as a multiple of 4.
    @Test
    public void NoteTest1() {
    	//test basic functionality of time representation: 4/8 becomes 2/4	
    	Note test = new Note("C", 10, "", 4, 8);
    	assertEquals("10C2/4",test.toString());
    }
    
    @Test
    public void NoteTest2() {
        //test basic functionality of time representation: 1/2 becomes 2/4  
        Note test = new Note("C", 10, "", 1, 2);
        assertEquals("10C2/4",test.toString());
    }
    
    @Test
    public void NoteTest3() {
        //test basic functionality of time representation: 6/9 becomes 8/12  
        Note test = new Note("C", 10, "", 6, 9);
        assertEquals("10C8/12",test.toString());
    }
    
    @Test
    public void NoteTest4() {
        //test accidental, octaves.   
        Note test = new Note("C", 2, ",,", 2, 4);
        assertEquals("2C,,2/4",test.toString());
    }
    
    @Test
    public void NoteTest5() {
    	//test basic functionality of time representation: 3/16 becomes 3/16
    	Note test = new Note("C", 10, "", 3, 16);
    	assertEquals("10C3/16",test.toString());
    }
    
    

}
