package exceptions;

public class InvalidIndexException extends JarvisException {

    public InvalidIndexException(String message) {
        super("    Master, this appears to be an invalid index. Please provide me a valid index.");
    }
    
}
