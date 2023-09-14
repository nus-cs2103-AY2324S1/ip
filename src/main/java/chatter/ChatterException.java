package chatter;

/**
 * Represents an exception that will be thrown by the chatbot.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ChatterException extends Exception {
    /**
     * A constructor to initialize the chatter.ChatterException class.
     *
     * @param message Error message to be displayed.
     */
    public ChatterException(String message) {
        super(message);
    }
}
