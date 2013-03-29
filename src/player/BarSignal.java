package player;

public class BarSignal implements BarLineObject {

	String value;//maybe we should change this to a token instead.. then also change constructor
	/**
	 * Constructor for BarSignal, it takes a string as it's value
	 * @param value
	 */
	BarSignal(String value){
		this.value= value; 
	}

	public boolean isNotestruct() {
        return false;
    }
	
	public boolean isVoiceIndicator(){
        return false;
    }
	
	public boolean isType(String type){
	    return type.equals(this.value);
	}
	
	@Override
	public String toString(){
	    return this.value;
	}

}
