package main.java.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class JukeDeadline extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[D] ";

    /** Deadline for Task. */
    private final LocalDateTime deadline;

    /**
     * Constructor for JukeDeadline.
     * @param task Task description
     * @param deadline Deadline for task
     */
    public JukeDeadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Constructor for JukeDeadline.
     * @param task Task description
     * @param deadline Deadline for task
     * @param completion Status of completion of the task
     */
    public JukeDeadline(String task, LocalDateTime deadline, boolean completion) {
        this(task, deadline);

        if (completion) {
            this.markAsComplete();
        }
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "D" + super.save() + "|" + deadline;
    }

    /**
     * String representation of this {@code JukeDeadline} object
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeDeadline.TASK_DESCRIPTOR + super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + " hrs)";
    }
}
