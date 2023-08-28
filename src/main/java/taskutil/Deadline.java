package taskutil;

import duke.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime limit;

    public Deadline(String title, LocalDateTime limit) {
        super(title);
        this.limit = limit;
    }

    @Override
    public String toString() {
        String time = String.format(" (by: %s)", limit.format(displayFormat));
        return "[D]" + super.toString() + time;
    }

    /**
     * Convert Deadline task to a string for storing in data file.
     * @return Formatted string with data for Deadline task.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + Parser.dateFormat(limit);
    }
}
