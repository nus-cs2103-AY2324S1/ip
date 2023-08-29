public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("InvalidDateException: ☹ OOPS!!! The datetime has to be in this format: dd-MM-yyyy HH:mm.");
    }
}
