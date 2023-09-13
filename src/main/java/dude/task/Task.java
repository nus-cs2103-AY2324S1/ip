package dude.task;

/**
 * Represents a task.
 * A <code>Task</code> object has a <code>String</code> description.
 */
public class Task {
    private String description;
    private boolean done;

    /**
     * Constructs a new Task object with the specified description.
     *
     * @param description A short description of the event.
     */
    Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getType() {
        return "task";
    }

    public String saveTask () {
        return this.description;
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
