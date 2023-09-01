package duke;

/**
 * The Task class represents a task.
 */
public class Task {
    /** Description of the task. **/
    private String description;
    /** Whether the task has been marked **/
    private boolean isMark;

    /**
     * Instantiates an instance of Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isMark = false;
    }

    /**
     * Returns whether the task has been marked with string X.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isMark ? "X" : " "); // mark done task with X
    }

    /**
     * Sets status of task to be marked or unmarked.
     */
    public void setMark(boolean isMark) {
        this.isMark = isMark;
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
