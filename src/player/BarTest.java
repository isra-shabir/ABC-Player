package player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.SequencePlayer;

public class BarTest {

    public void basicTest(){
        Bar darBar1 = new Bar();
        darBar1.add(new Note("E", "", "", 1, 1));
        darBar1.add(new Note("A", "", "", 1, 1));
        darBar1.add(new Note("A", "", "", 1, 1));
        darBar1.add(new Note("G", "", "", 1, 1));
        
        Bar darBar2 = new Bar();
        darBar2.add(new Note("F", "^", "", 1, 1));
        darBar2.add(new Note("F", "^", "", 1, 1));
        darBar2.add(new Note("F", "^", "", 1, 1));
        darBar2.add(new Note("F", "^", "", 1, 2));
        darBar2.add(new Note("G", "", "", 1, 2));
        
        Voice Aziz = new Voice("Voice Of Aziz");
        
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(140, Aziz.getMinTicksPerQuarter());
            
            myPlayer MrAhmed = new myPlayer("Cm", sqPlayer);
            
            Aziz.addToPlayer(0,0,MrAhmed);
            
            sqPlayer.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
