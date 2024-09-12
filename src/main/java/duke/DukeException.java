package duke;
/**
 * Represents exceptions that are specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMessages The specific error message for this exception.
     */
    public DukeException(String... errorMessages) {
        super(String.join(", ", errorMessages));
    }

    /**
     * Retrieves the error message of this exception, prefixed with "OOPS! :(".
     *
     * @return The prefixed error message.
     */
    @Override
    public String getMessage() {

        return "OOPS! :( " + super.getMessage();
    }
}
