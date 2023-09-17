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

    /**
     * Converts task into format to be saved in file.
     * @return format of task to be saved in file.
     */
    public String saveTask() {
        String doneStatus = this.isDone() ? "| 1 | " : "| 0 | ";
        return doneStatus + " " + this.description;
    }

    /**
     * Checks if task contains specified keyword.
     * @return boolean to represent if task contains the keyword.
     */
    public boolean containKeywords(String keywords) {
        String lowercaseKeywords = keywords.toLowerCase();
        String lowercaseDescription = this.description.toLowerCase();
        return lowercaseDescription.contains(lowercaseKeywords);
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A formatted string describing the task.
     */
    @Override
    public String toString() {
        String doneStatus = this.isDone() ? "[X]" : "[ ]";
        return doneStatus + " " + this.description;
    }
}
