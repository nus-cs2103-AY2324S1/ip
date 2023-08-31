package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

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
}
