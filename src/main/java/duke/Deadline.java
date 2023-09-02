package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of task.
 */
public class Deadline extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    /**
     * A public constructor of the deadline class.
     *
     * @param name extracted from the input
     * @param date of LocalDateTime type
     * @param input the user input
     */
    public Deadline(String name, LocalDateTime date, String input) {
        super(name, input);
        this.date = date;
    }

    /**
     * A string representation of the deadline class.
     *
     * @return a string
     */
    @Override
    public String toString() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        if (!this.getDone()) {
            return "[D][ ] " + this.getName() + "(by: " + this.date.format(formatter) + ")";
        } else {
            return "[D][X] " + this.getName() + "(by: " + this.date.format(formatter) + ")";
        }
    }
}

