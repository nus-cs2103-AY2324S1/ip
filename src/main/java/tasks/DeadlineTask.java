package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task.
 */
public class DeadlineTask extends Task {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");
    private final LocalDateTime deadline;

    /**
     * Constructor, initializes task description, deadline and isMarked.
     *
     * @param desc Task description.
     * @param deadline Deadline date/time of task.
     * @param isMarked 1 if is marked, 0 otherwise.
     */
    public DeadlineTask(String desc, LocalDateTime deadline, int isMarked) {
        super(desc);
        assert deadline != null : "Deadline should not be null.";
        this.deadline = deadline;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0,
                this.desc, this.deadline.format(INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DISPLAY_FORMAT));
    }

}
