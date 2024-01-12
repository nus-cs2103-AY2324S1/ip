package duke.exception;

/**
 * Represents an error when Duke encounters invalid arguments from user input.
 */
public class DukeInvalidArgumentException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "You provided invalid arguments :-(\n";
    }
}
