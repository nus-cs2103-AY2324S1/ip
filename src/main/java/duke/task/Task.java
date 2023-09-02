package duke.task;


/**
 * Represents the Task.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a Task with a specified description.
     *
     * @param description A string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon of a task.
     *
     * @return a String representing a status icon of a task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task.
     *
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task.
     *
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a String representing the Task that will be stored.
     *
     * @return the string representing the Task that will be stored.
     */
    public abstract String getInput();
}