package duke.exceptions;

public class DukeMissingArgumentException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s there is missing argument", super.toString());
    }
}