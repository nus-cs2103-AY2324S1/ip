package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by; //deadline of task

    /**
     * Creates a new deadline task that is not in LocalDate format.
     * @param description   Description of deadline task.
     * @param by    Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new deadline task that is in date format.
     * @param description   Description of deadline task.
     * @param date  Deadline of task in LocalDate format.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
