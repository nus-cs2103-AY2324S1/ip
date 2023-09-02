package data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DeadlineTask extends Task {
    /** Date of the deadline. */
    private LocalDateTime deadlineDate;
    /** Format for the DateTimeFormatter to follow. */
    private DateTimeFormatter formatter;

    /**
     * Constructs a new DeadlineTask with the specified task description and its deadline date.
     *
     * @param description The description of the task.
     * @param deadlineDate The datetime of the deadline of the task.
     */
    public DeadlineTask(String description, String deadlineDate) {
        super(description);
        try {
            // time should be in format dd/mm/yyyy HHMM(24h)
            this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.deadlineDate = LocalDateTime.parse(deadlineDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("There was an error parsing the date given.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the type of task as a String.
     *
     * @return The type of task.
     */
    @Override
    public String getType() {
        return "Deadline";
    }

    /**
     * Returns the formatted(dd/MM/yyyy HHmm) deadline date as a String.
     *
     * @return Datetime of the deadline.
     */
    @Override
    public String getDateTime() {
        return formatter.format(this.deadlineDate);
    }

    /**
     * Returns the string representation of the DeadlineTask.
     *
     * @return A string representation of this DeadlineTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "[D]" + super.toString() + " (by: " + stringFormatter.format(this.deadlineDate) + ")";
    }
}