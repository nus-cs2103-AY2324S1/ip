package pau.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task that has a description of the task and a deadline.
 */
public class Deadline extends Task {

    /**
     * Deadline for the deadline task.
     */
    protected String deadline;

    /**
     * Deadline for the deadline task saved as a Date.
     */
    protected LocalDate deadlineDate;

    /**
     * Constructs a deadline.
     *
     * @param description Description of the deadline.
     * @param deadline Deadline for the deadline task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        if (isValidDateFormat(deadline, "yyyy-MM-dd")) {
            this.deadlineDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.deadline = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    @Override
    public String writeToFile() {
        String delimiter = " | ";
        String status = this.isDone ? "1" : "0";
        return "D" + delimiter + status + delimiter + this.description + delimiter + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    /**
     * Checks if the given deadline is in the valid format to be parsed.
     *
     * @param deadline Deadline for the deadline task.
     * @param format Format for the date that is parsed.
     * @return The validity of the deadline to be parsed into a LocalDate.
     */
    public static boolean isValidDateFormat(String deadline, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            formatter.parse(deadline);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
