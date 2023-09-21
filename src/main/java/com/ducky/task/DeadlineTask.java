package com.ducky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {

    public static final String DATE_PATTERN = "MMM dd yyyy";
    private final LocalDate deadline;

    /**
     * Constructs a task with a deadline.
     *
     * @param desc Description of task.
     * @param deadline Deadline of task in LocalDate format.
     */
    public DeadlineTask(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public boolean dateFallsOn(LocalDate queryDate) {
        return this.deadline.equals(queryDate);
    }

    @Override
    public String toString() {
        return String.format(
                "[DEADLINE] %s (by: %s)",
                super.toString(),
                this.deadline.format(
                        DateTimeFormatter.ofPattern(DATE_PATTERN)
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
