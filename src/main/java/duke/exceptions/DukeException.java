package duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("OOPS!!!" + errorMessage);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + this.getMessage();
    }
}
