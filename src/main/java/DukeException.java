public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    DukeException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
