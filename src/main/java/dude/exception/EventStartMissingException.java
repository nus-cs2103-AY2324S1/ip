package dude.exception;

public class EventStartMissingException extends DudeException {
    public EventStartMissingException() {
        super("Please specify the event start date after the task\ndescription, prefixed by `/from`.");
    }
}
