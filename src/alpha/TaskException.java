package src.alpha;
// Class to handle exceptions with entering tasks
public abstract class TaskException extends AlphaException{
    public enum TaskType {
        DEADLINE,
        TODO,
        EVENT;
    }
    private MissingInfoException.TaskType task;
    public TaskException(String errorMessage, MissingInfoException.TaskType task) {
        super(errorMessage);
        this.task = task;
    }

    public TaskType getTask() {
        return this.task;
    }
}
