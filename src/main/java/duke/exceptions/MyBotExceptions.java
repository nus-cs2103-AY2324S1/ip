package duke.exceptions;

/**
 * The MyBotExceptions class is a custom exception class for handling various exceptions
 * specific to the chatbot application.
 */
public class MyBotExceptions extends Exception {

    /**
     * Constructs a MyBotExceptions instance with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public MyBotExceptions(String message) {
        super(message);
    }
}
