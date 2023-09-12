package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task{
    private final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline object with the given task description and deadline.
     *
     * @param task The description of the task.
     * @param deadline The deadline for the task in the format "yyyy-MM-dd HH:mm".
     * @throws DukeInvalidDateException If the deadline is not in the correct datetime format.
     */
    public Deadline(String task, String deadline) throws DukeInvalidDateException {
        super(task);
        try {
            this.deadline = LocalDateTime.parse(deadline, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException("Incorrect datetime format used.");
        }
    }

    /**
     * Generates a formatted string to save the Deadline task to file.
     *
     * @return A formatted string representing the Deadline task's details for saving to file.
     */
    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";
        return "D|" + completedString + description + "|" + deadline.format(parseFormatter);
    }

    /**
     * Generates a string representation of the Deadline task.
     *
     * @return A string displaying the Deadline task's details.
     */
    @Override
    public String toString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("d MMM uuuu h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }

    /**
     * Indicates whether some other object is "equal to" this Deadline instance.
     *
     * This method overrides the default equals implementation from the Object class.
     * Two Deadline instances are considered equal if they meet the following criteria:
     * 1. They reference the same object (i.e., identical references).
     * 2. The other object is an instance of the Deadline class.
     * 3. The 'isCompleted' status, 'description', and 'deadline' fields of both
     *    Deadline instances are equal.
     *
     * @param o The object to compare for equality with this Deadline instance.
     * @return true if the two Deadline instances are equal based on the specified criteria,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) o;

        return this.isCompleted == otherDeadline.isCompleted && this.description.equals(otherDeadline.description)
                && this.deadline.equals(otherDeadline.deadline);
    }

    /**
     * Returns a hash code value for the Deadline object.
     *
     * This method generates a hash code based on the 'isCompleted' status, 'description',
     * and 'deadline' of the Deadline object. It combines these properties to create
     * a unique hash code.
     *
     * @return The hash code value for the Deadline object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(isCompleted, description, deadline);
    }
}
