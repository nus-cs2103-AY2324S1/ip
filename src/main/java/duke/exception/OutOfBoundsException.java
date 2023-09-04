package duke.exception;

import duke.ui.Ui;

/**
 * Exception thrown when the index entered is out of range of the current list of tasks.
 */
public class OutOfBoundsException extends DukeException {

    /**
     * Constructor for OutOfBoundsException.
     *
     * @param index The index input.
     * @param taskCount The current number of tasks.
     */
    public OutOfBoundsException(int index, int taskCount) {
        super(String.format("☹ OOPS!!! %d is out of range. %s", index, Ui.getTaskCount(taskCount)));
    }

}
