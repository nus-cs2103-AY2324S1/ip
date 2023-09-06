package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.InvalidTaskCreationException;


/**
 * The `Deadline` class represents a task with a specific deadline in the Duke application.
 * It is a subclass of the `Task` class.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new `Deadline` task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Parses user input and creates a `Deadline` task.
     *
     * @param description The description of the `Deadline` task.
     * @param by          The deadline as a string to be parsed into a LocalDateTime object.
     * @return A `Deadline` task instance.
     * @throws InvalidTaskCreationException if the description or deadline is empty.
     * @throws DateTimeParseException       if there is an issue parsing the deadline string to LocalDateTime.
     */
    public static Deadline deadlineCon(String description, String by) throws InvalidTaskCreationException,
            DateTimeParseException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a Deadline Task cannot be empty.");
        } else if (by.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The deadline time of a Deadline Task cannot be empty.");
        } else {
            LocalDateTime deadlineDate = LocalDateTime.parse(by, Task.DATE_TIME_FORMATTER);
            return new Deadline(description, deadlineDate);
        }
    }

    /**
     * Gets the urgency date and time for the `Deadline` task, which is the deadline itself.
     *
     * @return The urgency date and time (deadline).
     */
    public LocalDateTime getUrgencyDate() {
        return this.by;
    }

    /**
     * Converts the `Deadline` task to a formatted string representation for display.
     *
     * @return A formatted string representing the `Deadline` task.
     */
    @Override
    public String toString() {

        // Format the LocalDateTime object to a string
        String formattedDate = this.by.format(Task.OUTPUT_FORMATTER);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
