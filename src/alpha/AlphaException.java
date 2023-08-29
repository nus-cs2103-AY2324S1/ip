package src.alpha;
// Class to handle Exceptions regarding ChatBot Alpha
public abstract class AlphaException extends Exception{
    public AlphaException(String errorMessage) {
        super(errorMessage);
    }
}
