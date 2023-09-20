package kevin.exception;

/**
 * A class responsible for the exceptions for Kevin.
 */
public class KevinException extends Exception {
    /**
     * Constructor to intialize KevinException.
     * @param errorMessage This is the message that will be displayed.
     */
    public KevinException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * {@inheritDoc}
     * @return Returns the detail message string of this throwable.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
