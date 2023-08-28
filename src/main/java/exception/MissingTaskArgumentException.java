package exception;

/**
 * Checked Exception for the event where the input arguments is missing.
 */
public class MissingTaskArgumentException extends MissingArgumentException {

    /**
     * Returns a string to inform users that there are missing arguments
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "  ☹ OOPS!!! You forgot to add the index number right after the command";
    }
}
