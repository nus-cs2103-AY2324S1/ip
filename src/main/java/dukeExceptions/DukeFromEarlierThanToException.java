package dukeExceptions;

/**
 * This Exception class is for when the 'from' time is after the 'to' time for Event tasks.
 */
public class DukeFromEarlierThanToException extends DukeException {
    public DukeFromEarlierThanToException(String message) {
        super(message);
    }
}

