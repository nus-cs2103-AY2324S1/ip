package dot.errors;

import java.util.function.Consumer;

/**
 * The exception for Dot.
 */
public class DotException extends Exception {

    private final TaskError taskError;

    /**
     * Constructor for DotException.
     *
     * @param message   This is the sub-message which the DotException hopes to display.
     * @param taskError This is the TaskError enum value which represents the category to
     *                  which this DotException belongs to.
     */
    public DotException(String message, TaskError taskError) {
        super(message);
        this.taskError = taskError;
    }

    /**
     * Overrides the toString method of object.
     * This method is extensible to further customise the error message in the future.
     *
     * @return String version of DotException.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }

    /**
     * Handles the error.
     * It takes the TaskError enum value which attaches the header
     * error message to the aforementioned sub-message and displays
     * it to the user in a non-cryptic manner.
     *
     * @param handleUiErrorMessage This is the GUI handler for error messages.
     */
    public void handleError(Consumer<String> handleUiErrorMessage) {
        handleUiErrorMessage.accept(this.taskError.getFullErrorMessage(this));
    }

}

