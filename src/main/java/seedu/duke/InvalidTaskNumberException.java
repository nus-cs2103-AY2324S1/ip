package seedu.duke;

/**
 * Encapsulates the exception where there is no such task in the task list from the
 * integer inputted by the user in mark/delete operations.
 */
public class InvalidTaskNumberException extends DukeException {
    /**
     * Creates an InvalidTaskNumberException.
     *
     * @param number The number given by the user.
     */
    public InvalidTaskNumberException(int number) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + number + " is not a valid task number.\n" +
                "____________________________________________________________");
    }
}
