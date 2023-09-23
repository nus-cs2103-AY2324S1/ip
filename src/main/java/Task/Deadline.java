package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class is used for tasks that have stipulated time to finish.
 */
public class Deadline extends Task {
    /**
     * The By.
     */
    private String by;
    private LocalDateTime deadlineInDateTime;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param by          the by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadlineInDateTime = LocalDateTime.parse(by, newFormat);
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() + " (by: " + printDateTimeFormat(deadlineInDateTime) + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, this.by);
    }
}
