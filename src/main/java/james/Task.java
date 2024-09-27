package james;

/**
 * Task class to represent a task
 */
public abstract class Task {

    /** The description of the task */
    private String description;

    /** The status of the task */
    private boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description A description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     *
     * @return String
     */
    public String toString() {
        String status = getStatusIcon();
        return "[" + status + "] " + this.description;
    }

    /**
     * Returns the status icon of the task if its done
     *
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the task as done **/
    public void mark() {
        this.isDone = true;
    }

    /** Unmarks the task as done **/
    public void unmark() {
        this.isDone = false;
    }

}
