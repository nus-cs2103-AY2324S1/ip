package taskutil;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Class for Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime limit;

    /**
     * Constructor for Deadline object.
     *
     * @param title Description of task.
     * @param limit Deadline of task as a LocalDateTime object.
     */
    public Deadline(String title, LocalDateTime limit) {
        super(title);
        this.limit = limit;
    }

    /**
     * Checks that input datetime is before deadline.
     *
     * @param localDateTime DateTime to be considered.
     * @return Whether deadline is under schedule of input datetime.
     */
    public boolean isUnderSchedule(LocalDateTime localDateTime) {
        return limit.isAfter(localDateTime);
    }

    @Override
    public String toString() {
        String time = String.format(" (by: %s)", limit.format(DISPLAY_FORMAT));
        return "[D]" + super.toString() + time;
    }

    /**
     * Converts Deadline task to a string for storing in data file.
     *
     * @return Formatted string with data for Deadline task.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + Parser.formatDate(limit);
    }
}
