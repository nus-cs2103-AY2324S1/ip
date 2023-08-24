public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     * @param taskName Name of todo.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the status of the task
     * @return Status of the task
     */
    @Override
    public String getTask() {
        return "To Do ->" + super.getTask();
    }
}
