package duke.exceptions;

/** Encapsulates exceptions due to missing task in the task list. */
public class MissingTaskException extends DukeException {
    /**
     * Constructor for Exceptions.MissingTaskException.
     * @param message Exception message.
     */
    public MissingTaskException(String message) {
        super(message);
    }

    /**
     * Returns the description of the exception.
     * @return Description of exception.
     */
    @Override
    public String getMessage() {
        return "Task cannot be found :(";
    }
}
