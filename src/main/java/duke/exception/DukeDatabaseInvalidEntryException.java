package duke.exception;

/**
 * Represents an error when Duke encounters invalid entry in database.
 */
public class DukeDatabaseInvalidEntryException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "There are invalid entries in your database ! :-(\n";
    }
}
