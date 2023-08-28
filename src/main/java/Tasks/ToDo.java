package Tasks;

public class ToDo extends Task {
    /**
     * Constructor for Tasks.ToDo.
     * @param taskName Name of todo.
     */
    public ToDo(String taskName, int isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns the string representation of the status of the task
     * @return Status of the task
     */
    @Override
    public String getTask() {
        return "To Do ->" + super.getTask();
    }

    @Override
    public String toString() {
        return super.toString().replace("/TASK", "todo ");
    }
}
