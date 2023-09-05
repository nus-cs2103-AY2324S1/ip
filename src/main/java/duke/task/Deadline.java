package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Task;

/**
 * Represents a task with a specific deadline.
 * A Deadline task contains a description and a deadline date and time.
 */
public class Deadline extends Task {
    private LocalDateTime dateAndTime;

    /**
     * Constructs a Deadline task with the specified description and deadline date and time.
     *
     * @param description The description of the Deadline task.
     * @param dateAndTime The deadline date and time.
     */
    public Deadline(String description, LocalDateTime dateAndTime) {
        super(description, Type.DEADLINE);
        this.dateAndTime = dateAndTime;
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
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + dateAndTime.format(formatter) + ")";
    }

    /**
     * Gets the date and time associated with this object.
     *
     * @return The date and time as a LocalDateTime object.
     */
    public LocalDateTime getDateAndTime() {
        return this.dateAndTime;
    }
}

