package dukeExceptions;

/**
 * This Exception class if for all the LocalDateTime parse errors
 */
public class DukeDateTimeParseException extends DukeException {
    public DukeDateTimeParseException(String message) {
        super(message);
    }
}
