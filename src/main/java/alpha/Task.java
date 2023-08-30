package alpha;

/**
 * Class representation of a task.
 * @author Wong Joon Hung
 */
public abstract class Task {

    private String description;

    private boolean isDone;

    /**
     * Constructor for Task class. Sets isDone to false since tasks are entered unchecked.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns description of task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the marked/unmarked status of the task.
     * @return "X" if marked, " " if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    /**
     * Marks the task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * {@inheritDoc}
     * Returns the String representation of a task being its marked status and description.
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
