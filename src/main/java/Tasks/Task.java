package Tasks;

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
     * @param title The title of the task.
     */
    public Task(String title, boolean isMarked) {
        this.title = title;
        this.isMarked = isMarked;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.isMarked = false;
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

    public abstract String toSave();
}

