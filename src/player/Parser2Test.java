package player;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.SequencePlayer;

public class Parser2Test {

    Note C = new Note("C", 10, "", 1, 4);
    Note E = new Note("E", 10, "", 1, 4);
    Note G = new Note("G", 10, "", 1, 4);
    
    Tuplet T1 = new Tuplet(3); // 1/4
    Tuplet T2 = new Tuplet(3);
    Chord MC1 = new Chord(); //1
    Chord MC2 = new Chord();
    
    Bar t1bar = new Bar();
    Bar t2bar = new Bar();
    Bar darBar1 = new Bar();
    Bar darBar2 = new Bar();
    
    VoiceIndicator toAziz = new VoiceIndicator("Aziz");
    VoiceIndicator toMuneeza = new VoiceIndicator("Muneeza");
    BarSignal plainBar = new BarSignal("OPENBAR");
    BarSignal repeatBar = new BarSignal("REPEAT");
    BarSignal endBar = new BarSignal("ENDBAR");
    BarSignal r1Bar = new BarSignal("FIRSTREPEAT");
    BarSignal r2Bar = new BarSignal("SECONDREPEAT");
    BarSignal repeatStart = new BarSignal("REPEATBEG");
        
    public void testMaterialConstructor(){
        
        T1.addNote(new Note("E", 10, "", 1, 8));
        T1.addNote(new Note("G", 10, "", 1, 8));
        T1.addNote(new Note("B", 10, "", 1, 8));
        
        T2.addNote(new Note("F", 1, "", 1, 8));
        T2.addNote(new Note("A", 1, "", 1, 8));
        T2.addNote(new Note("c", 1, "", 1, 8));
        
        MC1.addNote(new Note("E" ,10, ",", 1, 1));
        MC1.addNote(new Note("G" ,10, ",", 1, 1));
        MC1.addNote(new Note("B" ,10, ",", 1, 1));
        
        MC2.addNote(new Note("F" ,1, ",", 1, 1));
        MC2.addNote(new Note("A" ,1, ",", 1, 1));
        MC2.addNote(new Note("C" ,1, ",", 1, 1));
        
        t1bar.add(T1);
        t1bar.add(T1);
        t1bar.add(T1);
        t1bar.add(T1);
        
        t2bar.add(T2);
        t2bar.add(T2);
        t2bar.add(T2);
        t2bar.add(T2);
        
        darBar1.add(new Note("E", 10, "", 1, 4));
        darBar1.add(new Note("B", 10, "", 1, 4));
        darBar1.add(new Note("B", 10, "", 1, 4));
        darBar1.add(new Note("G", 10, "", 1, 4));
        
        darBar2.add(new Note("F", 1, "", 1, 4));
        darBar2.add(new Note("F", 1, "", 1, 4));
        darBar2.add(new Note("F", 1, "", 1, 4));
        darBar2.add(new Note("F", 1, "", 1, 8));
        darBar2.add(new Note("G", 10, "", 1, 8));
                     
        
    }
    
    //@Test
    public void basicTest(){
        
        testMaterialConstructor();
        
        ArrayList<String> voiceNames = new ArrayList<String>();
        voiceNames.add("Aziz");
        voiceNames.add("Muneeza");
        
        ArrayList<BarLineObject> objs = new ArrayList<BarLineObject>();
        objs.add(toAziz);
        objs.add(C);
        objs.add(E);
        objs.add(C);
        objs.add(G);
        objs.add(plainBar);
        objs.add(toMuneeza);
        objs.add(MC1);
        objs.add(repeatBar);
        objs.add(toAziz);
        objs.add(T1);
        objs.add(C);
        objs.add(T2);
        objs.add(G);
        objs.add(plainBar);
        
        Parser2 myParser2 = new Parser2(voiceNames);
        myParser2.parse(objs);
        ArrayList<Voice> voices = myParser2.getVoices();

        Song lalala = new Song(voices);
        
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(140, lalala.getMinTicksPerQuarter());
            myPlayer MrAhmed = new myPlayer("C", sqPlayer);
            lalala.addToPlayer(MrAhmed);
            sqPlayer.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Tests all repeat funtionality
     * Including standard repeat, 1-2- repeat, and with repeat beginnings and section ends. 
     */
    @Test
    public void advancedRepeatTest(){
        
        testMaterialConstructor();
        ArrayList<String> voiceNames = new ArrayList<String>();
        voiceNames.add("Aziz");
        
        ArrayList<BarLineObject> objs = new ArrayList<BarLineObject>();
        objs.add(C);
        objs.add(E);
        objs.add(C);
        objs.add(G);
        objs.add(plainBar);
        objs.add(C);
        objs.add(E);
        objs.add(C);
        objs.add(G);
        objs.add(endBar);
        objs.add(G);
        objs.add(G);
        objs.add(G);
        objs.add(C);
        objs.add(r1Bar);
        objs.add(C);
        objs.add(G);
        objs.add(G);
        objs.add(G);
        objs.add(r2Bar);
        objs.add(C);
        objs.add(E);
        objs.add(E);
        objs.add(E);
        objs.add(repeatBar);
        
        objs.add(C);
        objs.add(E);
        objs.add(C);
        objs.add(G);
        objs.add(repeatStart);
        objs.add(G);
        objs.add(C);
        objs.add(C);
        objs.add(G);
        objs.add(repeatBar);
        
        
               
        Parser2 myParser2 = new Parser2(voiceNames);
        myParser2.parse(objs);
        ArrayList<Voice> voices = myParser2.getVoices();

        Song lalala = new Song(voices);
        
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(140, lalala.getMinTicksPerQuarter());
            myPlayer MrAhmed = new myPlayer("C", sqPlayer);
            lalala.addToPlayer(MrAhmed);
            sqPlayer.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }
      
    /**
     * This will test behavior when voice switched occur in the middle of bars, and when we are repeating bars. 
     */
    //@Test
    public void RepeatWithVoiceSwitchTest(){
        testMaterialConstructor();
        ArrayList<String> voiceNames = new ArrayList<String>();
        voiceNames.add("Aziz");
        voiceNames.add("Muneeza");
        
        ArrayList<BarLineObject> objs = new ArrayList<BarLineObject>();
        objs.add(toAziz);
        objs.add(C);
        objs.add(E);
        objs.add(toMuneeza);
        objs.add(MC1);
        objs.add(toAziz);
        objs.add(C);
        objs.add(toMuneeza);
        objs.add(plainBar);
        objs.add(MC2);
        objs.add(endBar);
        objs.add(toAziz);
        objs.add(G);
        objs.add(repeatBar);
                     
        Parser2 myParser2 = new Parser2(voiceNames);
        myParser2.parse(objs);
        ArrayList<Voice> voices = myParser2.getVoices();
        Song lalala = new Song(voices);
        SequencePlayer sqPlayer;
        try {
            sqPlayer = new SequencePlayer(140, lalala.getMinTicksPerQuarter());
            myPlayer MrAhmed = new myPlayer("C", sqPlayer);
            lalala.addToPlayer(MrAhmed);
            sqPlayer.play();    
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
