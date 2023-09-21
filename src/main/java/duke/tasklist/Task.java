package duke.tasklist;

import java.time.LocalDate;

/**
 * Represents a generic task in the Duke application.
 * This class provides the base structure and common functionality for various types of tasks.
 */
public abstract class Task {
    private final String name;
    private boolean isMarked;
    private boolean isSnoozed;

    /**
     * Constructs a Task with the given name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
        this.isSnoozed = false;
    }

    /**
     * Creates a Todo task with the specified name.
     *
     * @param name The name or description of the task.
     * @return A new Todo task with the given name.
     */
    public static Task of(String name) {
        return new Todo(name);
    }

    /**
     * Creates a Deadline task with the specified name and deadline.
     *
     * @param name The name or description of the task.
     * @param time The deadline of the task.
     * @return A new Deadline task with the given name and deadline.
     */
    public static Task of(String name, LocalDate time) {
        return new Deadline(name, time);
    }

    /**
     * Creates an Event task with the specified name and event times.
     *
     * @param name The name or description of the task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @return A new Event task with the given name and event times.
     */
    public static Task of(String name, LocalDate startTime, LocalDate endTime) {
        return new Event(name, startTime, endTime);
    }

    /**
     * Marks the task as done.
     *
     * @return {@code true} if the task was successfully marked as done, {@code false} otherwise.
     */
    public boolean mark() {
        if (isMarked) {
            return false;
        } else {
            this.isMarked = true;
            return true;
        }
    }

    /**
     * Unmarks the task (marks it as not done).
     *
     * @return {@code true} if the task was successfully unmarked, {@code false} otherwise.
     */
    public boolean unmark() {
        if (!isMarked) {
            return false;
        } else {
            this.isMarked = false;
            return true;
        }
    }

    public boolean snooze() {
        if (isSnoozed) {
            return false;
        } else {
            this.isSnoozed = true;
            return true;
        }
    }
    /**
     * Returns the state of the task (done or not done) as a string representation.
     *
     * @return The state of the task as a string.
     */
    private String getState() {
        return isMarked ? "[X]" : "[ ]";
    }

    /**
     * Returns the text representation of the task.
     *
     * @return The text representation of the task.
     */
    public String getText() {
        int mark = isMarked ? 1 : 0;
        int snooze = isSnoozed ? 1 : 0;
        return mark + " | "
                + snooze + " | "
                + name;
    }

    protected boolean isSnoozed() {
        return isSnoozed;
    }

    /**
     * Checks if the task name contains the given search query.
     *
     * @param s The search query to match against the task name.
     * @return {@code true} if the task name contains the search query, {@code false} otherwise.
     */
    public boolean isMatch(String s) {
        return name.contains(s);
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return getState() + " " + name;
    }
}
