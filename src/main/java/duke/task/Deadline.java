package duke.task;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A Deadline task contains a description and a deadline date and time.
 */
public class Deadline extends Task {
    public LocalDateTime DateAndTime;

    /**
     * Constructs a Deadline task with the specified description and deadline date and time.
     *
     * @param description The description of the Deadline task.
     * @param DateAndTime The deadline date and time.
     */
    public Deadline(String description, LocalDateTime DateAndTime) {
        super(description, Type.DEADLINE);
        this.DateAndTime = DateAndTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task type, status icon, description, and deadline.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        // eg 2nd Dec 2019 6pm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + DateAndTime.format(formatter) + ")";
    }
}
