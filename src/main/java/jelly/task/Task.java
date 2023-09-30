package jelly.task;

/**
 * Corresponds to a task.
 */
public class Task {

    protected boolean isDone;
    protected String description;
    protected int priority = 1;

    /**
     * Constructor for a task, with description and completion status.
     *
     * @param description The description of the task, etc. "Do math homework".
     */
    public Task(String description) {
        assert description != null : "Task description should not be null.";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for whether a task is done or not.
     * @return A string "X" if done, empty space " " if not done.
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Getter for task description.
     * @return A string of the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for whether task is done or not.
     * @return A boolean, true if task is done, false if not.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks a task as done, changing isDone boolean to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done, changing isDone boolean to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Changes the priority of a task.
     * @param priority The priority of the task; 1 is high, 2 is medium, 3 is low.
     */
    public void changePriority(int priority) {
        this.priority = priority;
    }
    public String getPriority() {
        if (priority == 1) {
            return "(HIGH)";
        } else if (priority == 2) {
            return "(MEDIUM)";
        } else {
            return "(LOW)";
        }
    }
    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + description;
    }

    /**
     * Should not happen, as tasks should be either one of these: todo, deadline or event.
     * @return Error message.
     */
    public String writeToFile() {
        return "Error";
    }
}
