package duke.Exceptions;

public class NoTasksException extends LemonException {
    public NoTasksException(String message) {
        super(":( OPPS!!! There are no tasks to show in the list!");
    }
}
