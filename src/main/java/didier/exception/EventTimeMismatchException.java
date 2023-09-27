package didier.exception;

/**
 * Thrown to indicate that the event the user is defining does not make sense as
 * it ends before it starts.
 */
public class EventTimeMismatchException extends DidierException {
    public EventTimeMismatchException() {
        super("The end time of the event cannot be before the start time. ");
    }
}
