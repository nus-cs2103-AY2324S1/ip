package duke.task;
import java.time.LocalDateTime;
/**
 * Represents a task with a deadline.
 * The deadline task is a type of task that has a description and a specified deadline date and time.
 * It is used in a task management system to keep track of tasks that need to be completed by a certain time.
 */
public class Deadline extends Task {

    /**
     * The deadline date and time by which the task should be completed.
     */
    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description A description of the task.
     * @param by          The deadline date and time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string in the format: "[D] [Task Description] (by: [Deadline Date and Time])".
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by:" + super.localDateTimeToString(by) + ")";
    }
}
