public class MissingDateException extends DukeException {

    /**
     * Constructor for MissingDateException.
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

