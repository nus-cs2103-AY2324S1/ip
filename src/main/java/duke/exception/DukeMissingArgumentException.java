package duke.exception;

public class DukeMissingArgumentException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "You have missing arguments :-(\n";
    }
}
