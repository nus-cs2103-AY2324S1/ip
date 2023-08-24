/**
 * A class for Tasks.
 */
public class Task {
    /** Class field description that describes the task. */
    protected String description;
    /** Class field isDone that tells whether the task is marked. */
    protected boolean isDone;

    /**
     * Constructor to initialize the Task class.
     * @param description Describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the status icon of the task.
     * @return String "X" if task's isDone is true, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method that sets the task's isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that sets the task's isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Method that overrides default toString.
     * @return String representation of Task.
     */
    @Override
    public String toString() {

        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
