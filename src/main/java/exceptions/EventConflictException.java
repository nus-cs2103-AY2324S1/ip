package exceptions;

/**
 * Exception class for event conflicts.
 */
public class EventConflictException extends BocchiException {
    static final String EVENT_CONFLICT_ERROR_MSG = "There is a conflicting event time!\n"
            + "Please delete the old task or reschedule the current task.";

    /**
     * Constructs a new EventConflictException.
     */
    public EventConflictException() {
        super(EVENT_CONFLICT_ERROR_MSG);
    }
}
