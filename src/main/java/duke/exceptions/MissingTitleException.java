package duke.exceptions;

/** Encapsulates exceptions due to missing task titles. */
public class MissingTitleException extends DukeException {

    /**
     * Constructor for MissingTitleException.
     *
     * @param message Exception message.
     */
    public MissingTitleException(String message) {
        super(message);
    }

    /**
     * Returns the description of the exception.
     *
     * @return Description of exception.
     */
    @Override
    public String getMessage() {
        return "Title of the task is missing! Please give your task a name :)";
    }

}

