package player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.SequencePlayer;

public class BarTest {

    @Test
    public void basicTest(){
        
        Tuplet T1 = new Tuplet(3);
        T1.addNote(new Note("E", "", "", 1, 8));
        T1.addNote(new Note("G", "", "", 1, 8));
        T1.addNote(new Note("B", "", "", 1, 8));
        Tuplet T2 = new Tuplet(3);
        T2.addNote(new Note("F", "^", "", 1, 8));
        T2.addNote(new Note("A", "^", "", 1, 8));
        T2.addNote(new Note("C", "^", "", 1, 8));
        
        Bar t1bar = new Bar();
        t1bar.add(T1);
        t1bar.add(T1);
        t1bar.add(T1);
        t1bar.add(T1);
        Bar t2bar = new Bar();
        t2bar.add(T2);
        t2bar.add(T2);
        t2bar.add(T2);
        t2bar.add(T2);
                
        Bar darBar1 = new Bar();
        darBar1.add(new Note("E", "", "", 1, 4));
        darBar1.add(new Note("B", "", "", 1, 4));
        darBar1.add(new Note("B", "", "", 1, 4));
        darBar1.add(new Note("G", "", "", 1, 4));
        
        Bar darBar2 = new Bar();
        darBar2.add(new Note("F", "^", "", 1, 4));
        darBar2.add(new Note("F", "^", "", 1, 4));
        darBar2.add(new Note("F", "^", "", 1, 4));
        darBar2.add(new Note("F", "^", "", 1, 8));
        darBar2.add(new Note("G", "", "", 1, 8));
        
        
        
        
        
        
        Bar M1 = new Bar();
        Bar M2 = new Bar();
        
        Chord MC1 = new Chord();
        MC1.addNote(new Note("E" ,"", ",", 1, 1));
        MC1.addNote(new Note("G" ,"", ",", 1, 1));
        MC1.addNote(new Note("B" ,"", ",", 1, 1));
        
        M1.add(MC1);
        
        Chord MC2 = new Chord();
        MC2.addNote(new Note("F" ,"^", ",", 1, 1));
        MC2.addNote(new Note("A" ,"^", ",", 1, 1));
        MC2.addNote(new Note("C" ,"^", ",", 1, 1));
        
        M2.add(MC2);
        
        Voice Muneeza = new Voice("Voice of Muneeza");
        Muneeza.add(M1);
        Muneeza.add(M2);
        Muneeza.add(M1);
        Muneeza.add(M2);
        
        Voice Aziz = new Voice("Voice Of Aziz");
        Aziz.add(darBar1);
        Aziz.add(darBar2);
        
        Voice Eta = new Voice("Voice of Eta");
        Eta.add(t1bar);
        Eta.add(t2bar);
        Eta.add(t1bar);
        Eta.add(t2bar);
        
        
        
        Song InTheEnd = new Song();
        InTheEnd.add(Aziz);
        InTheEnd.add(Muneeza);
        //InTheEnd.add(Eta);
        
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(140, InTheEnd.getMinTicksPerQuarter());
            myPlayer MrAhmed = new myPlayer("Cm", sqPlayer);
            InTheEnd.addToPlayer(MrAhmed);
            sqPlayer.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    
}
