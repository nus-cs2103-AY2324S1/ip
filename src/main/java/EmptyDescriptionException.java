public class DukeException extends Exception {
    public DukeException(String message) {
        super("DukeException: " + message + "\n");
    }
}