package exception;

/**
 * Abstract Exception for the event where user input has missing arguments.
 */
public abstract class MissingArgumentException extends Exception{

    /**
     * Returns a string to inform users of the exception
     * @return string information to inform user of exception
     */
    @Override
    public abstract String toString();
}
