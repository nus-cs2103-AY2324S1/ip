package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which is a type of Task with a deadline date and time.
 */
public class Deadline extends Task {

    /**
     * Represents the type identifier for the Deadline.
     */
    private static final String TYPE = "[D]";

    /**
     * Date and time for the deadline of the task.
     */
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline object with a given task name and deadline date-time.
     *
     * @param name Name or description of the deadline task.
     * @param deadline Date and time for the deadline of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Converts the Deadline task to a formatted string suitable for saving in a file.
     *
     * @return Formatted string representing the task.
     */
    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.done ? 1 : 0
                , this.name, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return String representation of the task, including its type, status, name, and deadline.
     */
    @Override
    public String toString() {
        return TYPE + super.toString() + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
