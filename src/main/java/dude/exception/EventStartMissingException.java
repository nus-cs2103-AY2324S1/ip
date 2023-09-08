package dude.exception;

/**
 * Exception for add event command with missing start date/time.
 */
public class EventStartMissingException extends DudeException {
    public EventStartMissingException() {
        super("Please specify the event start date after the task\ndescription, prefixed by `/from`.");
    }
}
