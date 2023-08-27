public class DukeDateTimeFormatException extends DukeException {
    DukeDateTimeFormatException() {
        super("Date and Time are not in correct format. They must be in YYYY-MM-DD HH:MM format.");
    }
}
