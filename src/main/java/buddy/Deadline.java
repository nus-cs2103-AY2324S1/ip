package buddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a deadline date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * The constructor for a deadline task.
     * @param description The description of the deadline task
     * @param by The deadline date for the deadline task
     * @param isDone The status of the deadline task
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    private String getDateString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    @Override
    public void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public void updateDate(String fieldToUpdate, LocalDate newDate) {
        if (!fieldToUpdate.equalsIgnoreCase("by")) {

        }
        this.by = newDate;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("%s | %d | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description,
                this.getDateString(by, "yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString(by, "yyyy-MM-dd") + ")";
    }
}
