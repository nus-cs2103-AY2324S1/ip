package dude.exception;

public class EventEndMissingException extends DudeException {
    public EventEndMissingException() {
        super("Please specify the event end date after the task\n description and start date, prefixed by `/to`.");
    }
}
