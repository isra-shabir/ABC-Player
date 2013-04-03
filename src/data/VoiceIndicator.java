package data;

public class VoiceIndicator implements BarLineObject {
	
	/**
	 * Is BarLine object that signifies a change in voice. 
	 * Basically, is the BarlineObject implementation of the voice token.
	 * it tells VoiceParser that it is time to change to another voice.
	 */

    private String voiceName;
    
    /**
     * constructs a VoiceIndicator object
     * and gives it a name
     * @param voiceName
     */
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
