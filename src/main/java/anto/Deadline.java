package anto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a Deadline task.
 */
public class Deadline extends Task {

    /**
     * DateTime that the Task must be completed by.
     */
    protected LocalDateTime by;

    /**
     * Creates a Deadline task with specified description and by date.
     * @param description Description of the deadline task.
     * @param by String with DateTime format that represents when deadline task
     *           must be completed by.
     * @throws AntoException Exception thrown if by is not in valid DateTime format.
     */
    public Deadline(String description, String by) throws AntoException {
        super(description);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (!Parser.isValidDate(by, formatter)) {
            throw new InvalidDateTimeException();
        }
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Overridden toString method to represent deadline task in a new format.
     *
     * @return String representing deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)",
                this.getStatusIcon(),
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
