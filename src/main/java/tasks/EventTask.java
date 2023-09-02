package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event task.
 */
public class EventTask extends Task {

    protected static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor, initializes task description, start, end and isMarked.
     *
     * @param desc Task description.
     * @param start Start date/time of task.
     * @param end End date/time of task.
     * @param isMarked 1 if is marked, 0 otherwise.
     */
    public EventTask(String desc, LocalDateTime start, LocalDateTime end, int isMarked) {
        super(desc);
        this.start = start;
        this.end = end;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("E|%d|%s|%s|%s", this.isDone ? 1 : 0, this.desc,
                this.end.format(INPUT_FORMAT),
                this.start.format(INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.start.format(DISPLAY_FORMAT),
                this.end.format(DISPLAY_FORMAT));
    }

}
