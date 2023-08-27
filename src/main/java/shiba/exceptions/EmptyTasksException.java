package shiba.exceptions;

public class EmptyTasksException extends ShibaException {
    public EmptyTasksException() {
        super("You don't have any tasks yet!");
    }
}
