public class TaskDoesNotExistException extends DukeException {
    private static final String ERROR_MESSAGE = "I'm pretty sure that task doesn't exist...";
    public TaskDoesNotExistException() {
        super(ERROR_MESSAGE);
    }
}
