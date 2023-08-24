public class InvalidEventException extends Exception {
    public InvalidEventException() {
        super("☹ OOPS!!! You forgot to indicate the start and end date/time.");
    }
}
