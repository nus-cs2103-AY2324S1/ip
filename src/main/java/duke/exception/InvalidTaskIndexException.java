package duke.exception;

public class InvalidTaskIndexException extends DukeException {

    public InvalidTaskIndexException(int taskIndex) {
        super(" ☹ OOPS!!! I cannot find the task with index " + taskIndex + ".");
    }
}
