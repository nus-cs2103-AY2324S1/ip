package duke.exceptions;

import duke.Ui;

public class IncompleteDescriptionException extends Exception {
    @Override
    public String toString() {
        return Ui.divider + "\n"
                + "☹ OOPS!!! The description of the task is incomplete or incorrect."
                + "\n" + Ui.divider + "\n";
    }
}

