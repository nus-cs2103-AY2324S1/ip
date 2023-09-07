package duke.exceptions;

/**
 * Exception for when a user tries to access something that is not in the list.
 */
public class NotFoundException extends DukeException {
    public NotFoundException() {
        super("Sorry, the task ID you specified was not found in the list!");
    }
}
