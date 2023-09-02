package functional;

public class DukeException extends Exception {
    public DukeException() {
    }

    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return " ☹ OOPS!!! " + getMessage();
    }
}
