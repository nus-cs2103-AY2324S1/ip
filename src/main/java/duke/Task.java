package duke;

/**
 * The Task class represents a generic task item with a description and completion status.
 */
public class Task {
    Boolean done = false;
    String task;
    int taskId;
    static String line = "______________________________________________________________________________________\n";

    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param task The description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.taskId = taskId;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public Boolean isMarked() {
        return this.done;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unMark() {
        this.done = false;
    }

    /**
     * Returns a horizontal line used for separating sections.
     *
     * @return The horizontal line as a string.
     */
    public String line() {
        return line;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    public String getTask() {
        return "." + this.toString();
    }

    /**
     * Returns a string representation of the task with checkbox status.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + task;
    }
}
