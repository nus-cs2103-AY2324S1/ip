public class InvalidEventException extends Exception {
    public InvalidEventException() {
        super("OOPS!!! Please specify the description, start, and end time for this Event!");
    }
}
