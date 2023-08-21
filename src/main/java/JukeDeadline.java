package main.java;

/**
 * Represents a task with a deadline.
 */
public class JukeDeadline extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[D] ";

    /** Deadline for Task. */
    private String deadline;

    /**
     * Constructor for JukeDeadline.
     * @param task Task description
     * @param deadline Deadline for task
     */
    public JukeDeadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return JukeDeadline.TASK_DESCRIPTOR + super.toString() + " (by: " + this.deadline + ")";
    }
}
