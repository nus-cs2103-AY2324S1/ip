package kevin.taskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to represent the Deadline Task.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor to initialize Deadline.
     * @param isDone This is a boolean to mark whether the Deadline is done or not.
     * @param name This is the name description of the deadline.
     * @param deadline This is the deadline date for the deadline.
     */
    public Deadline(Boolean isDone, String name, LocalDateTime deadline) {
        super(isDone, name);
        this.deadline = deadline;
    }

    /**
     * Changes the date format to be shown.
     * @param deadline This is the date that wants to be formatted.
     * @return Returns a string of the new date format.
     */
    public String changeDeadlineFormat(LocalDateTime deadline) {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the Deadline Object to be written to the file.
     */
    public String toText() {
        return "Deadline - "  + isDone + " - " + name + " - " + deadline + System.lineSeparator();
    }

    /**
     * {@inheritDoc}
     * @return Returns a string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + changeDeadlineFormat(deadline) + ")";
    }
}
