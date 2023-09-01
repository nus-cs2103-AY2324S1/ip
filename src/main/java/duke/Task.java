package duke;

/**
 * The Task class represents a task.
 */
public class Task {
    /** Description of the task. **/
    private String description;
    /** Whether the task has been marked **/
    private boolean done;

    /**
     * Instantiates an instance of Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns whether the task has been marked with string X.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    /**
     * Sets status of task to be marked.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Sets status of task to be unmarked.
     */
    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns string to be written into the file.
     *
     * @return File information extracted from the task.
     */
    public String writeToFile() {
        return this.description;
    }
}
