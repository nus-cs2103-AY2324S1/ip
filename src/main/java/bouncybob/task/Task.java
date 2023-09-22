package bouncybob.task;

/**
 * Represents a task in the BouncyBob application.
 */
public class Task {
    private String name;
    private boolean isDone;
    private String note;

    /**
     * Constructs a new Task with the given name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.note = "";
    }

    /**
     * Toggles the done status of the task.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUnDone() {
        this.isDone = false;
    }

    /**
     * Sets the note of the task.
     */
    public void setNote (String note) {
        this.note = note;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description, which is the same as the name for a base task.
     */
    public String getDescription() {
        StringBuilder description = new StringBuilder(getName());
        if (note != null && !note.trim().isEmpty()) {
            description.append(" | Note: ").append(note);
        }
        return description.toString();
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the symbol representing the task type.
     *
     * @return null for the base task type.
     */
    public String getSymbol() {
        return null;
    }

    /**
     * Returns the note of the task.
     *
     * @return The note of the task.
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Converts the task to its file storage format.
     *
     * @return The string representation of the task in file storage format.
     */
    public String toFileFormat() {
        return getSymbol() + " | " + (isDone ? "1" : "0") + " | " + name + " | " + note;
    }
}
