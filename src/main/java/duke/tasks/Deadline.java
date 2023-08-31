package duke.tasks;

import duke.components.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Deadline is a task with a deadline date.
 */
public class Deadline extends Task{
    /**
     * Deadline date
     */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline object
     * @param name Name of deadline
     * @param by Date of deadline
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(Parser.DATETIME_FORMATTER));
    }

    @Override
    public String generateSaveString() {
        return String.format("D | %b | %s /by %s", isDone, name, by.format(Parser.DATETIME_FORMATTER));
    }

    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return by.toLocalDate().equals(date);
    }
}
