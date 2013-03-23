package player;

public interface NoteStruct{
    
    int timeNumerator = 0;
    int timeDenominator = 0;
    
    int getDenom();
    int getNumTicks(int ticksPerQuarter);
    int addToPlayer(int startingTick, int length, myPlayer player);
    
}