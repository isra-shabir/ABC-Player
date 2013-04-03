package data;

/**
 * BarSignal class is an object that represents a Bar token such 
 * as repeatbar, endbar etc
 *
 */

public class BarSignal implements BarLineObject {
	
	String value;
	
	/**
	 * Constructor for BarSignal, it takes a string as it's value
	 * @param value
	 */
	public BarSignal(String value){
		this.value= value; 
	}

	// a collection of methods checking what kind of BarSignal object it is

	public boolean isNotestruct() {
        return false;
    }
	
	public boolean isVoiceIndicator(){
        return false;
    }
	
	public boolean isType(String type){
	    return type.equals(this.value);
	}
	
	/**
	 * @return value - string representation of BarSignal object
	 */
	@Override
	public String toString(){
	    return this.value;
	}
}
