package alpha;
// Exception that is thrown when a task is inputted with missing information
public class MissingInfoException extends TaskException{

    public MissingInfoException(String errorMessage, TaskType task) {
        super(errorMessage, task);
    }
}
