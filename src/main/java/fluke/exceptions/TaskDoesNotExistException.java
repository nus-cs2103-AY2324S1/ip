package fluke.exceptions;

public class TaskDoesNotExistException extends FlukeException {
    private static final String ERROR_MESSAGE = "I'm pretty sure that task doesn't exist...";
    public TaskDoesNotExistException() {
        super(ERROR_MESSAGE);
    }
}
