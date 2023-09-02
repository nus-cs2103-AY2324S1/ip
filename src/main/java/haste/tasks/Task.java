package haste.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    protected boolean isComplete;
    protected String description;

    /**
     * Creates a Task object.
     *
     * @param description Task description.
     * @param isComplete Completion status of Task.
     */
    public Task(String description, boolean isComplete){
        this.description = description;
        this.isComplete = isComplete;
    }

    public void markDone() {
        isComplete = true;
    }

    public void markUndone() {
        isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public static boolean checkEmpty(String s) {
        return s == null || s.isBlank();
    }

    /**
     * Returns a String representation of Task to be stored.
     *
     * @return String representation of Task.
     */
    public String save() {
       return this.isComplete + "|" + this.description;
    }

}
