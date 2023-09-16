package duke.exceptions;

public class DukeInvalidArgumentException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s you have invalid input", super.toString());
    }
}