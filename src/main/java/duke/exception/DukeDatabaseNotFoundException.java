package duke.exception;

public class DukeDatabaseNotFoundException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Database not found ! :-(\n";
    }
}
