/**
 * Represents an EmptyDescriptionException.
 *
 * @author Pearlynn
 */

public class EmptyDescriptionException extends Exception {

    /**
     * Constructor for EmptyDescriptionException class.
     *
     * @param task The task with the empty description.
     */
    public EmptyDescriptionException(String task) {
        super("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }
}
