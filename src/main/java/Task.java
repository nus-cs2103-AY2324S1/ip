/**
 * The class that represents a task.
 *
 * @author Zhong Han
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor of the task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is marked as done, and " " otherwise.
     *
     * @return a string indicating the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markTaskDone() {
        this.isDone = true;
        System.out.println("\t  " + this);
    }

    /**
     * Marks the task as not done.
     */
    public void markTaskNotDone() {
        this.isDone = false;
        System.out.println("\t  " + this);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string comprising the status and description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
