package player;

public class VoiceIndicator implements BarLineObject {

    private String voiceName;
    
    public VoiceIndicator(String voiceName){
        this.voiceName = voiceName;
    }
    
    public String getVoiceName(){
        return this.voiceName;
    }
    
    public boolean isNotestruct() {
        return false;
    }
    
    public boolean isVoiceIndicator(){
        return true;
    }

    public boolean isType(String type) {
        return type == "VOICE";
    }
    
    @Override
    public String toString(){
        return "Voice Indicator: "+this.voiceName;
    }

}
