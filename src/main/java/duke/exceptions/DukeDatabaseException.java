package duke.exceptions;

public class DukeDatabaseException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s the database has issues", super.toString());
    }
}
