package jarvis.exceptions;

public class InvalidTaskFormatException extends JarvisException{
    public InvalidTaskFormatException(String message) {
        super("Master, please provide me with the correct format.");
    }
}
