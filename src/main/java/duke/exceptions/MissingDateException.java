package duke.exceptions;

/** Encapsulates exceptions due to missing dates. */
public class MissingDateException extends DukeException {

    /**
     * Constructor for Exceptions.MissingDateException.
     * @param message Exception message.
     */
    public MissingDateException(String message) {
        super(message);
    }

    /**
     * Returns the description of the exception.
     * @return Description of exception.
     */
    @Override
    public String getMessage() {
        return "Date of the task is missing! Please give your task a date :)";
    }

}

