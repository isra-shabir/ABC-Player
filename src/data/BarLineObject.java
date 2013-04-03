package data;
/**
 * 
 * @author azizkag
 * A BarLineObject interface that will be implemented by all objects that behave like bars  
 */
public interface BarLineObject {

	// each of these methods checks the type of BarLineObject
	
    boolean isNotestruct();
    boolean isType(String type);
    boolean isVoiceIndicator();

}
