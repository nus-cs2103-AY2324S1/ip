package duke.task;

/**
 * Abstract Task class.
 */
public abstract class Task {


    protected String description;
    protected boolean isDone;


    /**
     * Constructor with description.
     * @param description the description of the task being added.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor with description and whether it has been marked as done.
     * @param description the description of the task being added.
     * @param isDone whether it has been marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Returns the status icon of the task.
     * @return "X" if the task is marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Returns the description of the task.
     * @return te description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done by changing the boolean value to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by changing the boolean value to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the toString representation of the task.
     * @return the String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Creates the String representation of the task that will be written to the
     * text file.
     * @return String representation of the task.
     */
    public abstract String toWriteString();

}
