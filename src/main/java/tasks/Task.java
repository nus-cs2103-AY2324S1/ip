package tasks;

/**
 * The Task class stores the description of a task.
 *
 * @author Kenvyn Kwek
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Initializes a task.
     *
     * @param description Task description.
     * @param isDone If the task has already been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * String representation of a task.
     *
     * @return Details of a task.
     */
    public String toString() { // encapsulation principle
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Marks or unmarks a task.
     *
     * @param mark If the task is completed.
     */
    public void markTask(boolean mark) {
        isDone = (mark) ? true : false;
    }
}
