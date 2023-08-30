package jeeves.task;

/**
 * Contains logic related to the Task object.
 */
public class Task {
    /** Tracks the number of task objects to guarantee the assigned ID will be unique */
    private static int taskCount = 0;
    /** Unique identification number for the task */
    protected final int id;
    /** The description of the task */
    protected final String desc;
    protected boolean isDone;

    /**
     * Default constructor for the Task object.
     * Automatically assigns the instance's ID based on the global task count.
     *
     * @param desc Description of the task
     */
    public Task(String desc) {
        // Increments the taskCount and uses the value as the unique ID for the current task
        Task.taskCount += 1;
        id = Task.taskCount;
        this.desc = desc;
        isDone = false;
    }

    /**
     * Getter method for the global Task count.
     *
     * @return Number of Tasks initialized
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Getter method for the Task description.
     *
     * @return Description of the task
     */
    public String getDesc() {
        return desc;
    }


    /**
     * Getter method for the Task status.
     *
     * @return Status of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Setter method for the Task status
     *
     * @param isDone Status of the task
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     * Obtain a string representation of a Task object
     *
     * @return String representation of Task
     */
    @Override
    public String toString() {
        // Checks if a task has its status marked as complete
        // and displays the appropriate visual
        if (isDone) {
            return String.format("[X] " + this.getDesc());
        } else {
            return String.format("[ ] " + this.getDesc());
        }
    }

}
