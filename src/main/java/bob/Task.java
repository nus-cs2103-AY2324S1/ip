package bob;

/**
 * Reoresents a task that could be a todo, deadline or event
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description the name/description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the completion status of this task in the form of an icon.
     *
     * @return a string to indicate completion status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the integer representation of the completion status of this task in the form of an icon.
     *
     * @return 1 if Task is marked done.
     */
    public int getStatusInt() {
        return isDone? 1 : 0;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the Task type of the Task.
     *
     * @return the Task type.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Returns the due date of Deadlines.
     *
     * @return the due date of Deadlines.
     */
    public String getBy() {
        return "";
    }

    /**
     * Returns the starting date of Event.
     *
     * @return the starting date of Event.
     */
    public String getFrom() {
        return "";
    }

    /**
     * Returns the ending date of Event.
     *
     * @return the ending date of Event.
     */
    public String getTo() {
        return "";
    }

    /**
     * Returns the string representation of this task, including its completion status & description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks a task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
