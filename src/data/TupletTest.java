package data;

import org.junit.Test;
import static org.junit.Assert.*;


public class TupletTest {

	/**
	 * class to test functionalities described in Tuplet class
	 */
    //Test representation of time in simplest form, 
    //Maintaining a denominator as a multiple of 4.
    @Test
    public void TupletTest1() {
    	Note C = new Note("C", 10, "", 1, 4);
        Note D = new Note("D", 10, "", 1, 4);
        Note E = new Note("E", 10, "", 1, 4);
        
        //test tuplet time fixing, from 1/4 to 2/12
    	Tuplet t = new Tuplet(3);
    	t.addNote(C);
    	t.addNote(D);
    	t.addNote(E);
//    	System.out.println(t);
    	assertEquals("Tuplet 3\n10C2/12\n10D2/12\n10E2/12",t.toString());
    }
    
    @Test
    public void TupletTest2() {
    	Note C = new Note("C", 10, "", 1, 4);
        Note D = new Note("D", 10, "", 1, 4);
        
    	// test tuplet time fixing from 1/4 to 3/8
    	Tuplet t = new Tuplet(2);
    	t.addNote(C);
    	t.addNote(D);
    	
    	assertEquals("Tuplet 2\n10C3/8\n10D3/8",t.toString());
    
    }
    
    @Test
    public void TupletTest3() {
    	// test tuplet time fixing from 1/4 to 3/16

    	Note C = new Note("C", 10, "", 1, 4);
        Note D = new Note("D", 10, "", 1, 4);
        Note E = new Note("E", 10, "", 1, 4);
        Note F = new Note("F", 10, "", 1, 4);
        
    	Tuplet t = new Tuplet(4);
    	t.addNote(C);
    	t.addNote(D);
    	t.addNote(E);
    	t.addNote(F);

    	System.out.println(t.toString());
    	
    	assertEquals("Tuplet 4\n10C3/16\n10D3/16\n10E3/16\n10F3/16",t.toString());
    }  
}