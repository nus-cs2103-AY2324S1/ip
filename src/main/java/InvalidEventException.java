public class InvalidEventException extends MilException {
    public InvalidEventException() {
        super("☹ Oopsie! Please add your event with the following format: \"event (description) /from (date) /to (date)\".");
    }
}
