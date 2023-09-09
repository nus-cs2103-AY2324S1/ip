package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event task.
 */
public class EventTask extends Task {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");

    private final LocalDateTime start;
    private final LocalDateTime end;

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
        assert start != null : "Start should not be null.";
        assert end != null : "End should not be null.";
        this.start = start;
        this.end = end;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    public boolean checkIfTaskDueToday() {
        LocalDate currentDate = LocalDate.now();
        LocalDate taskDate = this.end.toLocalDate();
        return currentDate.equals(taskDate);
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
