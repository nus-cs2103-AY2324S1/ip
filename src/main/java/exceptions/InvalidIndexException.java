package exceptions;

/**
 * A DukeException for invalid index when marking, unmarking or deleting task.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructor, initializes the error message.
     *
     * @param listSize The size of the current task list.
     */
    public InvalidIndexException(int listSize) {
        super(String.format("Invalid index format.\n" +
                "Format should be: mark <task index> OR unmark <task index> " +
                "OR delete <task index>\n" +
                "where index is from 1 to %d.", listSize));
    }
}
