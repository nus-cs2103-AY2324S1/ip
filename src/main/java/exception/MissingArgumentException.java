package exception;

/**
 * Checked Exception for the event where there are missing arguments.
 */
public class MissingArgumentException extends Exception {
    @Override
    public String toString() {
        return "There are missing arguments to this command";
    }
}
