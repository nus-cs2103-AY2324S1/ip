package duke.exception;

/**
 * Represents an error when Duke encounters missing database.
 */
public class DukeDatabaseNotFoundException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "Database not found ! :-(\n";
    }
}
