package URBOI_PACKIN.TaskTypes;

import URBOI_PACKIN.Task;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    public Deadline(String description, LocalDateTime date) {
        super(description, date);
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
