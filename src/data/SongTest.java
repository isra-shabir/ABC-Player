package data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SongTest {
	
	/**
	 * this class tests the functionality of 
	 * methods in the Song class
	 */

	@Test
	public void test1() {
		
		// test basic functionality of getMinTicksPerQuarter() in Song class
	
		Voice voice1 = new Voice("adam");
		
		Bar bar1 = new Bar();
		
		Note note1 = new Note("A", 10, "'", 2,3);
		Note note2 = new Note("B", 10, ",", 3, 5);
	
		Chord chord1 = new Chord();	
		Tuplet tuplet1 = new Tuplet(2);
		
		chord1.addNote(note1);
		chord1.addNote(note2);
		tuplet1.addNote(note1);
		tuplet1.addNote(note2);
		
		
		bar1.add(chord1);
		bar1.add(tuplet1);
	
		voice1.add(bar1);
	
		ArrayList<Voice> voices = new ArrayList<Voice>();
		voices.add(voice1); // testing add method on the go
		
		int expectedOutput = 6;
		
		assertEquals(expectedOutput, voices.get(0).getMinTicksPerQuarter());		
	
	}
	
	@Test
	public void test2(){
		// testing basic functionality of the LCM method
		
		// LCM of 3 and 15 = 15
		
		int expectedLCM = 15;
		
		Song song = new Song();
		
		assertEquals(expectedLCM, song.lowestCommonMultiple(15,3));
	}

}
