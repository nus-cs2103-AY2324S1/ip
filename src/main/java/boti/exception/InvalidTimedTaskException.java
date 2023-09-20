package boti.exception;

/**
 * The Exception for invalid timed task command
 */
public class InvalidTimedTaskException extends Exception {
    /**
     * The constructor of InvalidTimedException
     */
    public InvalidTimedTaskException() {
        super("☹ OOPS!!! I'm sorry, but the input TimedTask is invalid :-(");
    }
}
