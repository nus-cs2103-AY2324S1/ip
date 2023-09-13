package dude.task;

/**
 * Represents a task.
 * A <code>Task</code> object has a <code>String</code> description.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object with the specified description.
     *
     * @param description A short description of the event.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getType() {
        return "task";
    }

    public String saveTask() {
        return this.description;
    }

    public boolean containKeywords(String keywords) {
        return this.description.contains(keywords);
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A formatted string describing the task.
     */
    @Override
    public String toString() {
        String doneStatus = "[ ]";
        if (this.isDone()) {
            doneStatus = "[X]";
        }
        return doneStatus + " " + this.description;
    }
}
