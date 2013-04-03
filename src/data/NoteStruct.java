package data;

import player.BarManager;

public interface NoteStruct extends BarLineObject{
	
	/**
	 * interface to be implemented by notes, chords, tuplets
	 */
    
    int timeNumerator = 0;
    int timeDenominator = 0;
    
    // methods defined in classes implementing this interface
    
    int getMinTicks();
    int getNumTicks(int ticksPerQuarter);
    int addToPlayer(int startingTick, int length, BarManager barManager);
    
}