package duke;

/**
 * The Task class represents a generic task item with a description and completion status.
 */
public class Task implements Comparable<Task> {
    private Boolean isDone = false;
    private final String task;

    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param task The description of the task.
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public Boolean isMarked() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unMark() {
        this.isDone = false;
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
        String checkbox = this.isDone ? "[X] " : "[ ] ";
        return checkbox + task;
    }

    @Override
    public int compareTo(Task otherTask) {
        return this.task.compareTo(otherTask.task);
    }
}
