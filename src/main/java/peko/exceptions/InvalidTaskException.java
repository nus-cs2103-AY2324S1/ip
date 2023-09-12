package peko.exceptions;

/**
 * The InvalidTaskException is a custom exception class that is thrown when an invalid task
 * is encountered in the application. This exception is for when the user tries to create a task
 * that is not in the right format.
 */
public class InvalidTaskException extends Exception {
    private String errorMessage;

    /**
     * Constructs an InvalidTaskException without a specific error message.
     */
    public InvalidTaskException() {}


    /**
     * Constructs an {@code InvalidTaskException} with a specific error message.
     *
     * @param s The error message describing the issue with the invalid task.
     */
    public InvalidTaskException(String s) {
        this.errorMessage = s;
    }

    /**
     * Overrides the default toString method to provide a general error message when the
     * exception is converted to a string.
     *
     * @return A general error message indicating that the provided task is invalid.
     */
    @Override
    public String toString() {
        return "That's Not a task BAKATARE!!!!";
    }

    /**
     * Provides a more detailed error message when the exception is converted to a string using
     * this method. This includes the general error message along with the specific error message
     * describing the issue with the invalid task.
     *
     * @return A detailed error message describing the issue with the invalid task.
     */
    public String errorToString() {
        return "Something is wrong with this task peko.Peko!\n" + errorMessage;
    }
}
