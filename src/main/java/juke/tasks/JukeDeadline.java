package juke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import juke.exceptions.JukeStateException;

/**
 * Represents a Deadline task. Deadline tasks contain a deadline which is
 * represented by a {@code LocalDateTime} object.
 */
public class JukeDeadline extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[D] ";

    /** Deadline for Task. */
    private final LocalDateTime deadline;

    /**
     * Creates an instance of {@code JukeDeadline}.
     *
     * @param task Task description
     * @param deadline Deadline for task
     */
    public JukeDeadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Creates an instance of {@code JukeDeadline}.
     *
     * @param task Task description
     * @param deadline Deadline for task
     * @param isCompleted Status of completion of the task
     * @throws JukeStateException if the task is already completed
     */
    public JukeDeadline(String task, LocalDateTime deadline, boolean isCompleted) {
        this(task, deadline);

        if (isCompleted) {
            this.setAsComplete();
        }
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "D" + super.save() + "|" + deadline;
    }

    /**
     * Returns String representation of this {@code JukeDeadline} object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeDeadline.TASK_DESCRIPTOR + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm"))
                + " hrs)";
    }
}
