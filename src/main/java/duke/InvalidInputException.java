package duke;

/**
 * This class is used to represent an exception thrown when an invalid command is given
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor for creating an InvalidInputException
     */
    public InvalidInputException() {
        super("Invalid command given.");
    }

}
