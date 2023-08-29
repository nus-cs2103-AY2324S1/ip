package todoify.taskmanager.task;

/**
 * A trivial task that represents to-dos. It has a title and can be marked as completed.
 */
public class Todo extends Task {
    /**
     * Constructor for a To-do task.
     *
     * @param title The title of the to-do.
     */
    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("<T> %s %s", this.getCompletedIndicatorString(), this.getTitle());
    }
}
