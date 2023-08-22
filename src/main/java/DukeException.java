public class DukeException extends Exception {
    public DukeException(String message) {
        super("OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
