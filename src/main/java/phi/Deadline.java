package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Deadline task type, and contains a String with a user-specified date as the deadline of a task
 */
public class Deadline extends Task {
    private String deadlineString;

    /**
     * Constructor for a new Deadline instance
     *
     * @param msg       Task message to be displayed
     * @param isDone    Boolean determining if task is completed
     * @param deadline  User-specified deadline of task
     */
    public Deadline(String msg, boolean isDone, String deadline) {
        super(Type.D,isDone, msg);
        this.deadlineString = deadline;
        LocalDate deadlineDate;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        try {
            deadlineDate = LocalDate.parse(deadlineString, inputFormat);
            this.deadlineString = deadlineDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            //System.out.println("Can't find a proper date format, using deadline as a String");
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadlineString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String outputFormat() {
        return String.format("%s|%b|%s|%s", taskType.toString(), done, taskName, deadlineString);
    }
}