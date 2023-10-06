package atlas.tasks;

import java.time.LocalDate;

import atlas.components.Parser;

/**
 * Todo is a Task with no dates
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object
     * @param name Name of Todo
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a new Todo object with reminders
     * @param name Name of Todo
     * @param reminderStartDate Date starting from which reminders should be sent
     */
    public Todo(String name, LocalDate reminderStartDate) {
        super(name, reminderStartDate);
    }

    /**
     * Returns string representation of Todo
     * @return String representation of Todo
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String generateSaveString() {
        if (hasReminder()) {
            assert reminderStartDate != null;
            return String.format("T | %b | %s /remind %s", isDone, name,
                    reminderStartDate.format(Parser.DATE_FORMATTER));
        }
        return String.format("T | %b | %s", isDone, name);
    }
}
