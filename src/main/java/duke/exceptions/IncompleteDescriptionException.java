package duke.exceptions;

public class IncompleteDescriptionException extends Exception {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of the task is incomplete or incorrect.";
    }
}

