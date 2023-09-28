package potato.task;

import potato.DateTime;
import potato.PotatoException;

/**
 * The Deadline class represents a task with a specified deadline.
 * It extends the Task class and adds deadline-related functionality.
 */
public class Deadline extends Task {
    protected DateTime deadline;
    protected String by;

    /**
     * Constructs a Deadline object with the provided description, deadline, completion status, and priority.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline for the task.
     * @param isDone      The completion status of the task.
     * @param priority    The priority of the task.
     */
    public Deadline(String description, String by, boolean isDone, String priority) {
        super(description, isDone, priority);
        this.deadline = new DateTime(by);
        this.by = by;
    }

    /**
     * Parses a user input string to create a Deadline object.
     *
     * @param input The user input string representing the deadline task.
     * @return A Deadline object created from the input.
     * @throws PotatoException If the input is empty or invalid.
     */
    public static Deadline parseDeadline(String input) {
        int indexBy = input.indexOf("/by");
        if (input.length() < 2) {
            throw new PotatoException("Bruh you wanna do air or something?");
        } else if (indexBy < 0) {
            throw new PotatoException("WHEN'S THE DUE DATE???");
        } else {
            assert input.length() > 2 : "input length should be > 1";
            return new Deadline(input.substring(0, indexBy - 1),
                    input.substring(indexBy + 4), false, "NIL");
        }
    }

    /**
     * Parses a saved representation of a Deadline object.
     *
     * @param input The string array containing the saved representation of the deadline task.
     * @return A Deadline object created from the saved data.
     */
    public static Deadline parseSavedDeadline(String[] input) {
        return new Deadline(input[2], input[3], input[1].equals("1"), input[4]);
    }

    /**
     * Converts the Deadline object to a string representation suitable for saving.
     *
     * @return A string representation of the Deadline object for saving.
     */
    @Override
    public String toSave() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by + " | " + priority.toUpperCase();
    }

    /**
     * Converts the Deadline object to a string for displaying to the user.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDate() + ")";
    }
}
