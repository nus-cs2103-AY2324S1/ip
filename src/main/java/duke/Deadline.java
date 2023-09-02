package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Abstraction for tasks that have deadlines. */
public class Deadline extends Task {

    /** duke.Deadline of task. */
    protected LocalDate deadline;

    /**
     * Constructor to make a new duke.Deadline task.
     *
     * @param description What the deadline task is about.
     * @param isDone Whether the deadline task is done.
     * @param deadline When is the deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline= deadline;
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "D " + isCompleted + " " + this.description +
                "/by" + this.deadline;
    }

    /**
     * Display string representation of task with deadline.
     *
     * @return String representation of task with deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
