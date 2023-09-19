package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidFormatException;



/**
 * Deadline instance of a task.
 */
public class Deadline extends Task {

    private final LocalDate dueDate;
    private String dueTime = "";

    /**
     * Constructs a Deadline with a given description. Completion of the task
     * is false by default.
     * @param description the description of the task
     * @param by the deadline the task must be completed by
     */
    public Deadline(String description, String by) throws InvalidFormatException {
        super(description);
        String[] temp = by.split("\\s+");
        try {
            this.dueDate = LocalDate.parse(temp[0]);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(e.getMessage() + "The correct format is YYYY-MM-DD HHHH(optional)");
        }
        if (temp.length == 2) {
            this.dueTime = temp[1];
        }
    }

    /**
     * Returns a formatted string of the status of the task.
     * @return string containing completion status, type, description, and deadline of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDueDate()
                + " " + this.dueTime + ")";
    }

    /**
     * Returns a string formatted in the way it is to be saved.
     * @return formatted string to be written into file
     */
    @Override
    public String toSaveFormat() {
        return "D | " + completionStatus() + " | " + this.description + " | "
                + this.dueDate + " " + this.dueTime;
    }

    /**
     * formats due date for display
     * @return string of formatted due date
     */
    private String formattedDueDate() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
