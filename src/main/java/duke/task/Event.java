package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.InvalidTaskCreationException;

/**
 * The `Event` class represents a task with a specific event time range in the Duke application.
 * It is a subclass of the `Task` class.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new `Event` task with the specified description and event time range.
     *
     * @param description The description of the task.
     * @param from        The start time of the event as a LocalDateTime object.
     * @param to          The end time of the event as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses user input and creates an `Event` task.
     *
     * @param description The description of the `Event` task.
     * @param from        The start time of the event as a string to be parsed into a LocalDateTime object.
     * @param to          The end time of the event as a string to be parsed into a LocalDateTime object.
     * @return An `Event` task instance.
     * @throws InvalidTaskCreationException if the description, start time, or end time is empty.
     * @throws DateTimeParseException       if there is an issue parsing the start or end time strings to LocalDateTime.
     */
    public static Event eventCon(String description, String from, String to) throws InvalidTaskCreationException,
            DateTimeParseException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a Event Task cannot be empty.");
        } else if (from.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The from time of a Event Task cannot be empty.");
        } else if (to.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The to time of a Event Task cannot be empty.");
        } else {

            LocalDateTime eventStartTimeDate = LocalDateTime.parse(from, Task.DATE_TIME_FORMATTER);
            LocalDateTime eventEndTimeDate = LocalDateTime.parse(to, Task.DATE_TIME_FORMATTER);
            return new Event(description, eventStartTimeDate, eventEndTimeDate);
        }
    }

    /**
     * Gets the urgency date and time for the `Event` task, which is the start time of the event.
     *
     * @return The urgency date and time (start time).
     */
    public LocalDateTime getUrgencyDate() {
        return this.from;
    }

    /**
     * Converts the `Event` task to a formatted string representation for display.
     *
     * @return A formatted string representing the `Event` task.
     */
    @Override
    public String toString() {
        String formattedDateFrom = this.from.format(Task.OUTPUT_FORMATTER);
        String formattedDateTo = this.to.format(Task.OUTPUT_FORMATTER);
        return "[E]" + super.toString() + " (from: " + formattedDateFrom + " to: " + formattedDateTo + ")";
    }
}
