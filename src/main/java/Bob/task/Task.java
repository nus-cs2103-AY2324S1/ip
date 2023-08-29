package Bob.task;

/**
 * The Task class encapsulates a task in real life. A task contains a description and keeps
 * track of whether the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task Class.
     * Instantiates an instance of a task that has not been completed.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of the Task Class.
     * Instantiates an instance of a task based on provided description and
     * sets the completion status of the class based on provided boolean.
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Mark this task as yet to be completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    public String convertToFileFormat() {
        return String.format("%s|%s", this.isDone? 1 : 0, this.description);
    }
}


