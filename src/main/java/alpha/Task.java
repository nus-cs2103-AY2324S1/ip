package alpha;

/**
 * Class representation of a task.
 *
 * @author Wong Joon Hung
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for Task class. Sets isDone to false since tasks are entered unchecked.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the marked/unmarked status of the task.
     *
     * @return "X" if marked, " " if not.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // Mark task done with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the String representation of a task, including its marked status and description.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
