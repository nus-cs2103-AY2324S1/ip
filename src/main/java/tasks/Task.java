package tasks;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The `Task` class represents a basic task with a description and completion status.
 * It serves as the base class for specific task types like `Todo`, `Deadline`, and `Event`.
 */
public class Task {
    protected String text;
    protected boolean isDone;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d-MMMM-yyyy h:mma", Locale.ENGLISH);

    /**
     * Constructs a `Task` object with the specified description.
     *
     * @param text The description of the task.
     */
    public Task(String text) {
        this.text = text;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return An "X" if the task is done, an empty string otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "");
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status The new completion status for the task.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Converts the `Task` object to a string in a save file format.
     *
     * @return A string representation of the `Task` object in save file format.
     */
    public String toSaveFormat() {
        return " | " + (this.isDone ? 1 : 0) + " | " + this.text;
    }

    /**
     * Returns a string representation of the `Task` object for displaying to the user.
     *
     * @return A string representation of the `Task` object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.text;
    }
}

