package data;

import static org.junit.Assert.*;
import org.junit.Test;

public class ChordTest {
	
	/**
	 * Testing methods in the Chord class
	 */

    Note note1 = new Note("A",1, ",", 2, 3);
    Note note2 = new Note("b",-2, "'", 2, 3);

	@Test
	public void test1() {
		//testing basic functionality of addNote method	    
	    Chord chord = new Chord();
	    chord.addNote(note1);
	  
	    assertEquals(chord.toString(), "Chord: \n1A,8/12\n");
	}
	
	@Test
<<<<<<< HEAD
	public void test2() {
		// testing basic functionality of addToPlayer method
		
		
	}
	
	@Test
	public void test3(){
		//testing basic functionality of chord's toString() method
		
		Note note1 = new Note("A",1, ",", 2, 3);
		Note note2 = new Note("B",0, "'", 1, 4);

		Chord chord = new Chord();
		chord.addNote(note1);
		chord.addNote(note2);
			
		String expected = "Chord: \n1A,8/12\n0B'1/4\n";
		
		assertEquals(expected, chord.toString());
	}	
=======
    public void test2() {
        //testing multiple notes
        
        Chord chord = new Chord();
        chord.addNote(note1);
        chord.addNote(note2);
      
        assertEquals(chord.toString(), "Chord: \n1A,8/12\n-2b'8/12\n");
    }
	
	@Test
    public void test3() {
        //testing multiple notes
        
        Chord chord = new Chord();
        chord.addNote(note1);
        chord.addNote(note2);
      
        assertEquals(chord.toString(), "Chord: \n1A,8/12\n-2b'8/12\n");
    }
	
>>>>>>> 65ada72a64a654f6efa933156e2bcce019445bdf
}
