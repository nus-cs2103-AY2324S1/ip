package duke.task;

/**
 * Represents a general task with a name and completion status.
 */
public class Task {
    private boolean done;
    private String name;

    /**
     * Constructs a new task with the specified name and sets its completion status
     * to false.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    /**
     * Constructs a new task with the specified name and completion status.
     *
     * @param name   The name of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.done = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * Returns a string representation of the task, including its completion status
     * and name.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String doneCheckbox = this.done ? "[X]" : "[ ]";
        return doneCheckbox + " " + this.name;
    }

    /**
     * Returns a string representation of the task in a format suitable for data
     * storage.
     *
     * @return A string representation of the task for data storage.
     */
    public String toDataString() {
        String isDone = this.done ? "1" : "0";
        return isDone + "|" + this.name;
    }
}
