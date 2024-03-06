package duke.exception;

/**
 * Represents an exception that is thrown when there is an error relating to the chatbot's database.
 */
public class DukeDatabaseException extends DukeException {

    /**
     * Constructs a DukeDatabaseException with an error message indicating a database error.
     */
    public DukeDatabaseException() {
        super(" ☹ OOPS!!! Error encountered with database.");
    }
}
