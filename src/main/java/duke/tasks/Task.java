package duke.tasks;

/**
 * Abstract base class representing a task in the Duke application.
 * Subclasses (e.g., Deadline, Event) extend this class to represent specific types of tasks.
 */
public abstract class Task {

    protected String title;
    protected boolean isMarked = false;

    /**
     * Constructs a new Task object with a title.
     *
     * @param title    The title of the task.
     * @param isMarked The marking status of the task.
     */
    public Task(String title, boolean isMarked) {
        this.title = title;
        this.isMarked = isMarked;
    }

    public void setMark(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Retrieves the marking status of the task.
     *
     * @return True if the task is marked as completed, false otherwise.
     */
    public boolean getIsMarked() {
        return this.isMarked;
    }

    /**
     * Retrieves the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Generates a string representation of the Task object.
     *
     * @return A formatted string indicating the status (marked or unmarked) and title of the task.
     */
    @Override
    public String toString() {
        String mark = this.isMarked ? "[X] " : "[ ] ";
        return mark + title;
    }

    /**
     * Generates a formatted string to represent the Task object for saving.
     *
     * @return A formatted string for saving the Task object.
     */
    public abstract String toSave();
}
