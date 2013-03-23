package player;

import sound.SequencePlayer;

public class myPlayer {
    
    String keySignature;
    SequencePlayer sq;
    
    public myPlayer(String keySig, SequencePlayer sq){
        this.keySignature = keySig;
        this.sq = sq;
    }

    public void addNote(int startingTick, int length, String basenote,
            String accidental, String octave) {

        
        // TODO Auto-generated method stub
        
    }

}
