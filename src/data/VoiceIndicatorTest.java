package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class VoiceIndicatorTest {
	
	/**
	 * mandatory test file for VoiceIndicatorTest
	 * testing non-trivial method
	 */

	@Test
	public void test() {
		// testing basic functionality of toString()
		
		VoiceIndicator voiceInd = new VoiceIndicator("adam");
		
		String expectedOutput = "Voice Indicator: adam";
		
		assertEquals(expectedOutput, voiceInd.toString());
		
	}

}

