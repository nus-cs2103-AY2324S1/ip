package carbonbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task that need to be done before a specific date/time
 */
public class Deadline extends Task {
    private static final String TASK_ICON = "[D]";
    private static final DateTimeFormatter SERIALIZE_DT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_DT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected LocalDateTime by;

    /**
     * Constructs a deadline task with the provided description and due datetime.
     *
     * @param description Description of the task
     * @param by          Due date / time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s",
                super.isDone ? 1 : 0,
                super.description,
                this.by.format(SERIALIZE_DT_FORMATTER));
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString()
                + " (by: " + by.format(OUTPUT_DT_FORMATTER) + ")";
    }
}
