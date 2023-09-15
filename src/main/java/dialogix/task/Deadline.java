package dialogix.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task with a deadline in Dialogix.
 */
public class Deadline extends Task {
    private String deadlineBy;
    private Date deadlineDate;

    /**
     * Initializes a Deadline task with a description and a deadline specified as a string.
     *
     * @param description The description of the task.
     * @param deadlineBy  The deadline specified as a string.
     */
    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    /**
     * Initializes a Deadline task with a description and a deadline specified as a date.
     *
     * @param description  The description of the task.
     * @param deadlineDate The deadline specified as a date.
     */
    public Deadline(String description, Date deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Gets the formatted output string for saving in a file.
     *
     * @return The formatted output string.
     */
    @Override
    public String getOutputFormat() {
        if (deadlineBy == null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return String.format("D | %d | %s | %s", isDone() ? 1 : 0,
                    getDescription(), dateFormatter.format(deadlineDate));
        }
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getDescription(), deadlineBy);
    }

    /**
     * Gets a string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        if (deadlineDate != null) {
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("[D]%s (by: %s)", super.toString(), dtf.format(deadlineDate));
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
        }
    }
}
