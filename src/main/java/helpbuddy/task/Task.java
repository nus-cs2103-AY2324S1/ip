package helpbuddy.task;

/**
 * An abstract class representing a Task. This class provides a basic structure for various tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with specified String description.
     * @param description A string that indicates task name.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns String icon that represents if Task is done.
     * @return "X" if Task is done; " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the status of task.
     */
    public void updateDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns String representing the Task object.
     * @return a string representation of description, isDone.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns String representing the Task object to be saved in a file.
     * @return a string representation of Task object attributes.
     */
    public abstract String stringifyTask();

    /**
     * Compares the description of Task object to String S. Return true if and only if s is found in the description;
     * false otherwise.
     * @param s String representation of Task's description to match.
     * @return true if description has String s; false otherwise.
     */
    public boolean hasDescription(String s) {
        return this.description.contains(s);
    }

}
