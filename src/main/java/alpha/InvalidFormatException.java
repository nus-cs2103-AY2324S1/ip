package alpha;
// Exception thrown when a task is input with an invalid format
public class InvalidFormatException extends TaskException{

    public InvalidFormatException(String errorMessage, TaskType task) {
        super(errorMessage, task);
    }
}
