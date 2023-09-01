package duke;

/**
 * The class represents exceptions used in situations where error occurs in CheeChat.
 */
public class CheeException extends Exception {

    /** Constructs a new CheeException with the specified error message.
     * @param message The detail message that describes the reason for this exception.
     *                It should provide context about the error condition.
     */
    public CheeException(String message){
        super(message);
    }
}
