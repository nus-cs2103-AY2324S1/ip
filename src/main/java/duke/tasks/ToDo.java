package duke.tasks;

public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     * @param taskName Name of todo.
     */
    public ToDo(String taskName, int isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns the string representation of the status of the task.
     * @return Status of the task.
     */
    @Override
    public String getTask() {
        return "To Do ->" + super.getTask();
    }

    /**
     * Returns the string representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return super.toString().replace("/TASK", "todo ");
    }
}
