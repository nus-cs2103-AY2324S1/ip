package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime time;

    /**
     * Constructor for Deadline class
     *
     * @param task the task description
     * @param deadlineDetails the deadline of the task
     */
    public Deadline(String task, String deadlineDetails) {
        super(task);
        LocalDateTime dueDateTime = Parser.formatDate(deadlineDetails);
        this.time = dueDateTime;
    }

    /**
     * A getter to get the deadline of the task
     *
     * @return the deadline of this task
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String timeToString = Parser.dateToString(this.time);
        return "[D]" + super.toString() + " (by: " + timeToString + ")";
    }
}