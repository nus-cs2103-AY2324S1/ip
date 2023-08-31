package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, start time, and deadline. It inherits from the Task class.
 */
public class Event extends Task{
    private final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime start;
    private LocalDateTime deadline;

    /**
     * Constructs an Event object with the provided event details.
     *
     * @param task The description of the event.
     * @param start The start time of the event in "yyyy-MM-dd HH:mm" format.
     * @param deadline The deadline time of the event in "yyyy-MM-dd HH:mm" format.
     * @throws DukeInvalidDateException If the start or deadline time has an incorrect format.
     */
    public Event(String task, String start, String deadline) throws DukeInvalidDateException {
        super(task);
        try {
            this.start = LocalDateTime.parse(start, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException("Incorrect datetime format used for /by.");
        }
        try {
            this.deadline = LocalDateTime.parse(deadline, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException("Incorrect datetime format used for /to.");
        }
    }

    /**
     * Returns a string representation of the Event task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the Event task's details for file storage.
     */
    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";

        return "E|" + completedString + task + "|" + start.format(parseFormatter) + "|" + deadline.format(parseFormatter);
    }

    /**
     * Returns a string representation of the Event task including its start and deadline times.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("d MMM uuuu h:mm a");
        String formattedDeadline = this.deadline.format(toStringFormatter);
        String formattedStart = this.start.format(toStringFormatter);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedDeadline + ")";
    }
}
