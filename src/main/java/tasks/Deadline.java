package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a specific due date.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate parsedBy; // New field to store parsed LocalDate

    /**
     * Constructs a Deadline instance with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by          The due date of the task in string format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        parseBy();
    }

    /**
     * Constructs a Deadline instance with the specified description, due date, and completion status.
     *
     * @param description The description of the task.
     * @param by          The due date of the task in string format.
     * @param mark        The completion status of the task.
     */
    public Deadline(String description, String by, boolean mark) {
        super(description, mark);
        this.by = by;
        parseBy();
    }

    /**
     * Parses the due date string into a LocalDate object using multiple date formats.
     */
    private void parseBy() {
        DateTimeFormatter[] dateFormats = {
          DateTimeFormatter.ofPattern("yyyy-MM-dd"),
          DateTimeFormatter.ofPattern("MMM dd yyyy")
        };

        for (DateTimeFormatter dateFormat : dateFormats) {
            try {
                parsedBy = LocalDate.parse(by, dateFormat);
                break;
            } catch (DateTimeParseException e) {
                parsedBy = parsedBy;
            }
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        String dateString = parsedBy != null
          ? parsedBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
          : by;

        return "[D]" + super.toString() + " by: " + dateString;
    }
}
