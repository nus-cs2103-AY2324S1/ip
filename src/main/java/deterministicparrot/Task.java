package deterministicparrot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task in the Deterministic Parrot application.
 * This is an abstract class that serves as the base for different types of tasks.
 */
abstract public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    /**
     * Gets the name (description) of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done (undone).
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A formatted string indicating the completion status and name of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
