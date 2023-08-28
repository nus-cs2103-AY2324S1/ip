/**
 * Represents an EmptyTaskException.
 *
 * @author Pearlynn
 */

public class EmptyTaskException extends Exception {

    /**
     * Constructor for EmptyTaskException class.
     *
     * @param command The command with the empty task.
     */
    public EmptyTaskException(String command) {
        super("☹ OOPS!!! Pls select a task to " + command + ".");
    }
}
