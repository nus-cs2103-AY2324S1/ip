package duke.exceptions;
/**
 * Represents the exception thrown when the user inputs a task without any textual description.
 */
public class MissingDescriptionException extends Exception{
    /**
     * Creates a MissingDescriptionException object.
     * 
     * @param task The task that is missing a description.
     */
    public MissingDescriptionException(String task) {
        super("OOPS!!! The description of a " + task + " cannot be empty.");
    }
}