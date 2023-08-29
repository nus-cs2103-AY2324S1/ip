package seedu.duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class
 */
public class Deadline extends Task {
    private LocalDateTime byDate;

    /**
     * Deadline constructor
     *
     * @param description user input
     * @param byDate dateline of task
     * @param isMarked is task marked
     */
    public Deadline(String description, LocalDateTime byDate, boolean isMarked) {
        super(description, isMarked);
        this.byDate = byDate;
    }

    /**
     * Formats description for writing to duke.txt
     *
     * @return proper string format
     */
    public String writeFormat() {
        int isDone = super.isMarked() ? 1 : 0;
        String formattedDate = byDate.format(super.timeFormat);
        return "D" + " | " + isDone + " | " + super.getDescription() + " | " + formattedDate;
    }

    public LocalDate getByDate() {
        return this.byDate.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
