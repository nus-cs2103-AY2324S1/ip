package linus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import linus.exception.LinusException;

public class Deadline extends Task {
    protected String type = "linus.task.Deadline";
    protected LocalDate by = null;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @throws LinusException
     */
    public Deadline(String description, String by) throws LinusException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new LinusException("☹ OOPS!!! Please specify the deadline in the correct format: yyyy-mm-dd");
        }
    }

    /**
     * Returns the icon representing the type of task.
     * @return String
     */
    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    /**
     * Returns a String representation of the Deadline object.
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
