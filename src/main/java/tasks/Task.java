package tasks;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import duke.Duke;

/**
 * The `Task` class represents a task in the Duke application.
 */
public abstract class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * A flag indicating whether the task is marked as done.
     */
    private boolean isDone;

    /**
     * Constructs a `Task` with the given description and initializes it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public DateTimeFormatter getDateTimeformatter() {
        return Duke.getDateTimeFormatter();
    }

    /**
     * Gets the status icon representing the task's completion status.
     *
     * @return The status icon ("[X]" for done, "[ ]" for not done).
     */
    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Generates a string representation of the `Task`.
     *
     * @return A string representation of the task, including its status icon and description.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return `true` if the task is done, `false` otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Checks if the task description contains a specified keyword.
     *
     * @param keyword The keyword to search for within the task description.
     * @return `true` if the task description contains the keyword, `false` otherwise.
     */
    public boolean hasKeyWord(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Checks if this `Task` is equal to another object.
     *
     * @param o The object to compare to.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) o;
        return this.isDone == otherTask.isDone && this.description.equals(otherTask.description);
    }

    /**
     * Generates a hash code for this `Task`.
     *
     * @return A hash code for the task, including its description and completion status.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}
