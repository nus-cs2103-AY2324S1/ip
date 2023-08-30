package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, which is a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    private LocalDate byDate;

    /**
     * Constructs a Deadline object with the specified description and deadline time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date/time for the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
    }
    /**
     * Constructs a Deadline object with the specified description and deadline time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date/time for the deadline task.
     * @param bool        The completion status for the deadline task.
     */
    public Deadline(String description, String by, Boolean bool) {
        super(description, bool);
        this.byDate = LocalDate.parse(by);
    }

    // Adapted from https://www.baeldung.com/java-8-date-time-intro
    @Override
    public String toString() {
        LocalDate tmp = this.byDate;
        return "[D]" + super.toString() + " (by: " + tmp.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the task's description and completion status for saving.
     *
     * @return A formatted string containing completion status and description.
     */
    @Override
    public String getSaveDescription() {
        String tmp = "D " + super.getSaveDescription() + " | " + this.byDate + "\n";
        return tmp;
    }
}
