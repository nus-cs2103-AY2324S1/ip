package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Encapsulates a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for an Event object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }

    /**
     * Constructs an Event object with a given description, start time, end time.
     *
     * @param command The command to create the event.
     * @return The event object constructed from the given command.
     */
    public static Event createEventFromCommand(String command) throws DukeException {
        if (command.length() <= 6) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (!command.contains(" /from ")) {
            throw new DukeException("The event command must contain a /from.");
        } else if (!command.contains(" /to ")) {
            throw new DukeException("The event command must contain a /to.");
        } else if (command.endsWith(" /from ")) {
            throw new DukeException("The event command must contain a description after /from.");
        } else if (command.endsWith(" /to ")) {
            throw new DukeException("The event command must contain a description after /to.");
        }

        String[] tmpSplit = command.substring(6).split(" /from ");
        String taskDescription = tmpSplit[0];
        String[] split = tmpSplit[1].split(" /to ");
        String from = split[0];
        String to = split[1];
        return new Event(taskDescription, from, to);
    }

    /**
     * Constructs an Event object with the given description, start time, end time, and done status.
     *
     * @param storageString The string from duke.txt representing a row of data for an event.
     * @return The event object constructed from the given string.
     */
    public static Event createEventFromStorage(String storageString) {
        String[] split = storageString.split(" \\| ");
        String isDone = split[1];
        String taskDescription = split[2];
        String from = split[3];
        String to = split[4];
        Event event = new Event(taskDescription, from, to);
        if (isDone.equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        String fromString = from.format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm"));
        String toString = to.format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }

    /**
     * Returns the string representation of the event for storage in duke.txt.
     *
     * @return The string representation of the event for storage in duke.txt.
     */
    public String toStorageString() {
        String fromString = from.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        String toString = to.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "E" + super.toStorageString() + " | " + fromString + " | " + toString;
    }
}
