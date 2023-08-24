public class Task {

    /** Completion status of the task. */
    private boolean isDone;
    /** Title of the task. */
    private String taskName;

    /**
     * Constructor for Task.
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the status of the task.
     * @return Status of the task.
     */
    public String getTask() {
        String output = (isDone ? " [X] " : " [ ] ") + this.taskName;
        return output;
    }

    /**
     * Marks the status of the task as completed.
     * @return Dialogue to confirm the completion status of the task.
     */
    public String markAsDone() {
        this.isDone = true;
        return "'" + this.taskName + "'" + " is completed! Good job :)";
    }

    /**
     * Marks the status of the task as uncompleted.
     * @return Dialogue to confirm the completion status of the task.
     */
    public String markAsUndone() {
        this.isDone = false;
        return "'" + this.taskName + "'" + " is now not completed :(";
    }
}
