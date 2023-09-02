package functional;

public class DukeException extends Exception {
    public DukeException() {
        // super(message);
    }

    public String toString() {
        return " ☹ OOPS!!! " + getMessage();
    }
}
