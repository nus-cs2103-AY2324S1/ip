package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeInvalidArgumentException;

/**
 * An abstract class that represents a task.
 */
public abstract class Task {

    /** The date/time formatter for output to the user. */
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm");

    /**
     * The date/time formatter for parsing user input.
     * This format is used to format dates internally.
     */
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** The description of the task. */
    private String description;

    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Creates a new Task object.
     *
     * @param input The description of the task.
     */
    public Task(String input) {
        assert input != null && input != "" : "Description of a task should not be empty.";
        this.description = input;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the save format of the task, to be written to the save file.
     *
     * @return The save format of the task.
     */
    public abstract String getSaveFormat();

    /**
     * Parses the date input from the user.
     *
     * @param input The date input from the user.
     * @return The parsed date input.
     * @throws DukeInvalidArgumentException If the date input is invalid.
     */
    public static String parseDateInput(String input) throws DukeInvalidArgumentException {
        try {
            return LocalDateTime.parse(input, parseFormatter).format(parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                    "Your date seems to be formatted wrongly. Please follow this format: yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Returns the string representation of the date, to be shown to the user.
     *
     * @param input The date input from the user.
     * @return The string representation of the date.
     */
    public static String getDateOutputString(String input) {
        return LocalDateTime.parse(input, parseFormatter).format(outputFormatter);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + " " + this.description;
    }
}
