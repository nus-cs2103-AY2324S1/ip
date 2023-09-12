package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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

        return "E|" + completedString + description + "|" + start.format(parseFormatter) + "|" + deadline.format(parseFormatter);
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

    /**
     * Indicates whether some other object is "equal to" this Event instance.
     *
     * This method overrides the default equals implementation from the Object class.
     * Two Event instances are considered equal if they meet the following criteria:
     * 1. They reference the same object (i.e., identical references).
     * 2. The other object is an instance of the Even class.
     * 3. The 'isCompleted' status, 'description', 'start' and 'deadline' fields of both
     *    Event instances are equal.
     *
     * @param o The object to compare for equality with this Event instance.
     * @return true if the two Event instances are equal based on the specified criteria,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) o;

        return this.isCompleted == otherEvent.isCompleted && this.description.equals(otherEvent.description)
                && this.deadline.equals(otherEvent.deadline) && this.start.equals(otherEvent.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCompleted, description, start, deadline);
    }
}
