package exception;

import exception.MilException;

public class NoTaskIndexException extends MilException {
    public NoTaskIndexException() {
        super("☹ Oopsie! You did not input the task index.");
    }
}
