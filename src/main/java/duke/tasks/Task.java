package duke.tasks;

/**
 * Represents the Abstract Task Class.
 * Has three subclasses Event, Todo and Deadline.
 *
 * @author Shishir
 */
abstract public class Task {

    /** Description of the task. */
    private String description;

    /** Completion status of the task. */
    private boolean completed;

    /**
     * Constructs the task class.
     * @param action Description of the task.
     */
    public Task(String action) {
        this.description = action;
        this.completed = false;
    }

    /**
     * Constructs the task class.
     * @param action Description of the task.
     * @param status Status of completion of the task.
     */
    public Task(String action, String status) {
        this.description = action;
        this.completed = status.equals("X");
    }

    /**
     * Returns the string representation of the task.
     * @return Required string representation.
     */
    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.description;
    }

    /**
     * Returns the string representation in the file format.
     * @return Required string representation in file format.
     */
    public String toFile() {
        return " | " + (this.completed ? "X" : "O") + " | " + this.description;
    }

    /** Sets the completion status of the task to true. */
    public void completeTask() {
        this.completed = true;
    }

    /**
     * Returns the status of a task.
     * @return Status of completion.
     */
    public boolean isCompleted() { return this.completed; }

    /** Sets the completion status of the task to false. */
    public void revertTask() {
        this.completed = false;
    }

    public boolean contains(String name) {
        return this.action.contains(name);
    }
}
