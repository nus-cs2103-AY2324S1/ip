package duke.exception;

public class DukeDatabaseInvalidEntryException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "There are invalid entries in your database ! :-(\n";
    }
}
