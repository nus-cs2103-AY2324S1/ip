package duke.exceptions;

import duke.Ui;

public class InvalidTaskIndexException extends Exception {
    @Override
    public String toString() {
        return "☹ OOPS!!! You keyed in an invalid task index!";
    }
}
