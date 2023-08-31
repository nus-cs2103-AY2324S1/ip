package bruno.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * The Event class is responsible for all Event tasks.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime by;

    /**
     * Creates a new instance of the Event class.
     *
     * @param description Description of the task.
     * @param from        Start time of the task.
     * @param by          End time of the task.
     */
    public Event(String description, String from, String by) {
        super(TaskType.EVENT, description);
        this.from = this.convertToLocalDateTime(from);
        this.by = this.convertToLocalDateTime(by);
    }

    /**
     * {@inheritDoc}
     * @return String representation of the task.
     * @throws DateTimeException Thrown if LocalDateTime values 'from' and 'by' cannot be converted to string.
     */
    @Override public String getString() throws DateTimeException {
        return "[E]" + super.getString() + " (from: " + convertDateTimeToString(from) + " to: "
                + convertDateTimeToString(by) + ")";
    }

    /**
     * {@inheritDoc}
     * @return String representation of the task, as required by the file.
     * @throws DateTimeException Thrown if LocalDateTime values 'from' and 'by' cannot be converted to string.
     */
    @Override public String getFileString() throws DateTimeException {
        return "E|" + super.getFileString() + "|" + from.toString().replace('T', ' ') + "|" + by.toString()
                .replace('T', ' ');
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }
}
