package duke.exception;

import duke.ui.Ui;

public class OutOfBoundsException extends DukeException {

    public OutOfBoundsException(int index, int taskCount) {
        super(String.format("☹ OOPS!!! %d is out of range. %s", index, Ui.getTaskCount(taskCount)));
    }

}
