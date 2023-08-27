package exception;

import exception.MilException;

public class EmptyTaskException extends MilException {
    public EmptyTaskException() {
        super("☹ Oopsie! You did not include any task description.");
    }
}
