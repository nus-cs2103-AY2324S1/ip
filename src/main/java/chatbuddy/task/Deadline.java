package chatbuddy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline represents a Deadline object in ChatBuddy.
 * A deadline is a task that has a deadline.
 */
public class Deadline extends Task {

    /** The formatter used for date inputs. */
    private static final DateTimeFormatter FORMATTER_DATE_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /** The formatter used for date outputs. */
    private static final DateTimeFormatter FORMATTER_DATE_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /** The deadline of the task. */
    protected LocalDate by;

    /**
     * Creates an instance of a Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER_DATE_OUTPUT) + ")";
    }

    /**
     * Returns deadline task information in format for saving.
     * The format is D | [1 if completed, 0 if not completed] | [task description] | [by].
     *
     * @return The deadline task information in format for saving.
     */
    @Override
    public String getInformationForSaving() {
        return "D | " + super.getInformationForSaving() + " | " + by.format(FORMATTER_DATE_INPUT);
    }
}
