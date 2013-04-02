package player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ChordTest {
	
	/**
	 * Testing methods in the Chord class
	 */

	@Test
	public void test1() {
		//testing basic functionality of addNote method
		Note note1 = new Note("A",1, ",", 2, 3);
		
		
	    ArrayList<Note> notesExpected = new ArrayList<Note>();
	    notesExpected.add(note1);
	    
	    Chord chord = new Chord();
	    chord.addNote(note1);
	  
	    assertEquals(notesExpected.toString(), chord.notes.toString());
	    
	}
	
	@Test
	public void test2() {
		// testing basic functionality of addToPlayer method
		
	}
	
	@Test
	public void test3(){
		//testing basic functionality of toString() method
		
		Note note1 = new Note("A",1, ",", 2, 3);
		Note note2 = new Note("B",0, "'", 1, 4);
//
//		Chord chord = new Chord();
//		chord.addNote(note1);
//		chord.addNote(note2);
		
		System.out.println("notesToString:" + note1.toString());
		
		
//		String expected = "Chord: A^,23\n Chord: B='14\n";
		
		
		
		
		
		
		
	}
	
	
}
