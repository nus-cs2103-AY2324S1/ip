package task;

/**
 * A generic task, containing a name and completion status.
 */
public class Task {
    public static final String DIVIDER = "%!%";
    private final String name;
    private boolean isComplete;

    /**
     * Initializes a task.
     * @param name name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    /**
     * Initializes a task with its status.
     * @param name name of the task.
     * @param isComplete whether the task is complete.
     */
    public Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    /**
     * A setter to mark a task as done.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * A setter to mark a task as not done.
     */
    public void markUndone() {
        this.isComplete = false;
    }

    /**
     * Creates a readable string interpretation of the Task.
     * @return a readable Task in String form.
     */
    public String toString() {
        if (isComplete) {
            return ("[X] " + name);
        }
        return ("[ ] " + name);
    }

    /**
     * Formats the task for saving to a file.
     * @return file-formatted task String.
     */
    public String fileFormat() {
        if (isComplete) {
            return ("TRUE" + DIVIDER + name);
        }
        return ("FALSE" + DIVIDER + name);
    }
}
