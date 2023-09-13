package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.util.DateParser;


/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private String deadlineBy;

    private LocalDate deadlineDate;

    private LocalDateTime deadlineDateTime;

    /**
     * Constructs a Deadline object with the task description and deadline.
     *
     * @param description Description of the task.
     * @param deadlineBy Deadline of the task.
     */
    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineDate = DateParser.parseDate(deadlineBy);
        this.deadlineDateTime = DateParser.parseDateTime(deadlineBy);
        this.deadlineBy = deadlineBy;
    }

    /**
     * Formats the string representation of deadline object to write to the file.
     *
     * @return String representation of the deadline object to be written to the file.
     */
    @Override
    public String toFileString() {
        String type = "D";
        return type + " | " + (isDone() ? "1" : "0") + " | " + this.description + " | " + this.deadlineBy;
    }

    /**
     * The string representation of deadline object.
     *
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        if (deadlineDateTime != null) {
            String formattedDate = deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return String.format("[D]%s (by: %s)", super.toString(), formattedDate);
        } else if (deadlineDate != null) {
            String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return String.format("[D]%s (by: %s)", super.toString(), formattedDate);
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
        }

    }
}

