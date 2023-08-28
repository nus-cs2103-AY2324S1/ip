package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The duke.tasks.Deadline class containing tasks
 * with deadlines.
 * @author: Shishir
 **/
public class Deadline extends Task {

    /** The deadline of the task. **/
    private LocalDateTime deadline;

    /** The constructor
     * @param deadline The deadline of the task.
     * @param description The description of the task.
     * **/
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /** The constructor.
     * @param description The description of the task.
     * @param status The status of completion.
     * @param deadline The deadline of the task.
     **/
    public Deadline(String description, LocalDateTime deadline, String status) {
        super(description, status);
        this.deadline = deadline;
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[Deadline] " + super.toString()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    public String toFile() {
        return "D" + super.toFile() + " | " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }
}
