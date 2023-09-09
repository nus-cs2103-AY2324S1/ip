package com.ducky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {

    private final LocalDate deadline;

    /**
     * Constructs a task with a deadline.
     * @param desc Description of task.
     * @param deadline Deadline of task in LocalDate format.
     */
    public DeadlineTask(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy")
                ));
    }

    @Override
    public String getSaveFormat() {
        return String.format(
                "D | %s | %s",
                super.getSaveFormat(),
                this.deadline
        );
    }
}
