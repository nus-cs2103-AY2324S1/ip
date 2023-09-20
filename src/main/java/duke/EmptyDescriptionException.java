package duke;

/**
 * Represents an exception when a description is empty in Duke.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs a new EmptyDescriptionException with the specified detail message.
     *
     * @param taskType The type of task which description that cannot be empty.
     */
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The " + taskType + " cannot be empty.");
    }

    /**
     * Returns a string representation of this EmptyDescriptionException, which includes the error message.
     *
     * @return A string representation of the exception's detail message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
