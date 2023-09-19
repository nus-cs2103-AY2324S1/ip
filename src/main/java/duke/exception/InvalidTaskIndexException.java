package duke.exception;

/**
 * Represents an exception that occurs when an invalid task index is provided.
 */
public class InvalidTaskIndexException extends DukeException {

    /**
     * Constructs an InvalidTaskIndexException with an error message indicating the invalid task index.
     *
     * @param taskIndex The invalid task index provided by the user.
     */
    public InvalidTaskIndexException(int taskIndex) {
        super(" ☹ OOPS!!! I cannot find the task with index " + taskIndex + ".");
    }
}
