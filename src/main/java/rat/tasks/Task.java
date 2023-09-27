package rat.tasks;

/**
 * This class encapsulates a task.
 * @author Keagan
 */
public abstract class Task {

    /**
     * The name of the task.
     */
    private String name;

    /**
     * The status of the task.
     */
    private boolean isDone;

    /**
     * Constructor for a task.
     * @param name The name of the task.
     */
    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Method that changes the status of the task to done.
     */
    protected void markDone() {
        this.isDone = true;
    }

    /**
     * Method that changes the status of the task to not done.
     */
    protected void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a task.
     * The String representation of a task is its name prefixed by its status.
     * @return A String representation of a task.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + this.name;
    }

    /**
     * Returns a String representation of a task that is used to write to a file.
     * @return The String representation of a task in the format used to write to a file.
     */
    public String formatForFile() {
        String status = isDone ? "1" : "0";
        return status + ", " + this.name;
    }

}
