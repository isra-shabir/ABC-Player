package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SongTest {

	@Test
	public void test1() {
		
		// test basic functionality of getMinTicksPerQuarter()
		
		Voice voice1 = new Voice("adam");
		Voice voice2 = new Voice("eve");
		
		Bar bar1 = new Bar();
		Bar bar2 = new Bar();
		
		Note note1 = new Note("A", 10, "'",2,3);
		Note note2 = new Note("B", 10, ",", 3, 5);
		
		Note note3 = new Note("E", 10, "'", 1, 4);
		Note note4 = new Note("F", 10, ",", 1, 5);

		Chord chord1 = new Chord();
		Chord chord2 = new Chord();
		Tuplet tuplet1 = new Tuplet(2);
		Tuplet tuplet2 = new Tuplet(3);
		
		bar1.add(note1);
		bar1.add(note2);
		bar1.add(chord1);
		bar1.add(tuplet1);
		
		bar2.add(note3);
		bar2.add(note4);
		bar2.add(chord2);
		bar2.add(tuplet2);
		
		voice1.add(bar1);
		voice2.add(bar2);
		
		System.out.println(voice1.getMinTicksPerQuarter());
		
//		int expectedOutput = 
		
//		assertEquals()
		
	
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
