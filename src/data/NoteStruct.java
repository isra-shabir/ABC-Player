package data;

import player.BarManager;

public interface NoteStruct extends BarLineObject{
    
    int timeNumerator = 0;
    int timeDenominator = 0;
    
    int getMinTicks();
    int getNumTicks(int ticksPerQuarter);
    int addToPlayer(int startingTick, int length, BarManager barManager);
    
}