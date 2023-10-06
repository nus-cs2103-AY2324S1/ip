package atlas.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import atlas.components.Parser;

/**
 * Deadline is a task with a deadline date.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Constructs a new Deadline object
     * @param name Name of deadline
     * @param by Datetime of deadline
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructs a new Deadline object with reminders
     * @param name Name of deadline
     * @param by Datetime of deadline
     * @param reminderStartDate Date starting from which reminders should be sent
     */
    public Deadline(String name, LocalDateTime by, LocalDate reminderStartDate) {
        super(name, reminderStartDate);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(Parser.DATETIME_FORMATTER));
    }

    @Override
    public String generateSaveString() {
        if (hasReminder()) {
            assert reminderStartDate != null;
            return String.format("D | %b | %s /by %s /remind %s", isDone, name,
                    by.format(Parser.DATETIME_FORMATTER), reminderStartDate.format(Parser.DATE_FORMATTER));
        }
        return String.format("D | %b | %s /by %s", isDone, name, by.format(Parser.DATETIME_FORMATTER));
    }

    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return by.toLocalDate().equals(date);
    }
}
