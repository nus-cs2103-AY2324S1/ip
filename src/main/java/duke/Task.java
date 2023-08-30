package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class Task {
    /**
     * Name of task.
     */
    private String taskName;

    /**
     * Boolean to track whether the task has been marked as done.
     */
    private boolean done;

    /**
     * format of output.
     */
    private static final String DATETIME_OUTPUT_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Formatter object to format output.
     */
    public static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT);

    /**
     * Constructor for the task class.
     * Tasks are initialised as not done.
     *
     * @param name Name fo the task.
     */
    public Task(String name) {
        this.taskName = name;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.done = false;
    }

    /**
     * Returns a string representation of the task.
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Returns a string representation of the task to be saved.
     */
    public abstract String toSaveStateString();

    /**
     * Returns the done state of the task.
     *
     * @return A boolean representation of whether the task is done.
     */
    public boolean getDone() {
        return this.done;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns whether the task is on a date.
     *
     * @return A boolean representation of whether the task is on a given date.
     */
    public abstract boolean isOnDate(LocalDate date);
}
