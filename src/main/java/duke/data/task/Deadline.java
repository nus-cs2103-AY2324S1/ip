package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class is a child class of Task that represents tasks
 * with a specific deadline date.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor to initialize Deadline.
     *
     * @param description Description of the deadline task.
     * @param deadline Date of deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        try {
            LocalDate d1 = LocalDate.parse(deadline);
            this.deadline = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.deadline = deadline;
        }
    }

    public String getDeadline() {
        return this.deadline;
    }
    @Override
    public String saveString() {
        return "D" + super.saveString() + " | " + this.deadline + "\r\n";
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
