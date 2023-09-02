package duke.task;

import duke.exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline instance of a task.
 */
public class Deadline extends Task{

    private final LocalDate DUE_DATE;
    private String dueTime = "";

    /**
     * Constructs a Deadline with a given description. Completion of the task
     * is false by default.
     *
     * @param description The description of the task
     * @param by The deadline the task must be completed by
     */
    public Deadline(String description, String by) throws InvalidFormatException {
        super(description);
        String[] temp = by.split("\\s+");
        try {
            this.DUE_DATE = LocalDate.parse(temp[0]);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(e.getMessage() + "The correct format is YYYY-MM-DD HHHH(optional)");
        }
        if (temp.length == 2) {
            this.dueTime = temp[1];
        }
    }

    /**
     * Returns a formatted string of the status of the task.
     * @return String containing completion status, type, description,
     * and deadline of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.DUE_DATE.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + this.dueTime  + ")" ;
    }

    /**
     * Returns a string formatted in the way it is to be saved.
     * @return Formatted string to be written into file
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description + " | "
                + this.DUE_DATE + " " + this.dueTime;
    }
}
