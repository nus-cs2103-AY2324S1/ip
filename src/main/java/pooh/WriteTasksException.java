package pooh;

/**
 * Exception thrown when an error occurs during the writing of tasks to a file.
 * <p>
 * This exception should be caught and handled by displaying an appropriate
 * error message to inform the user of the failure.
 * </p>
 */
public class WriteTasksException extends PoohException {

    /**
     * Returns a string representation of the exception, which can be used
     * to display an error message to the user.
     *
     * @return A string describing the issue encountered during file writing.
     */
    @Override
    public String toString() {
        return "      An error has occurred whilst writing to file. :-(";
    }
}
