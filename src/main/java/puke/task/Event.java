package puke.task;

import java.time.LocalDateTime;

import puke.managers.PukeException;

/**
 * A Task class that has a start and end time attached to it.
 */
public class Event extends Task {
    private static final int CONSTRUCT_SIZE = 3;
    private static final String EVENT_LABEL = "[E]";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a task with a start and end time
     *
     * @param all All Strings from the remainder of the input line after the command string.
     * @throws PukeException If an incorrect format is used.
     */
    public Event(String[] all) throws PukeException {
        super(EVENT_LABEL, all[0]);
        try {
            from = LocalDateTime.parse(all[1].split("from ")[1]);
            to = LocalDateTime.parse(all[2].split("to ")[1]);
        } catch (Exception DateTimeParseException) {
            throw new PukeException();
        }
    }

    /**
     * Creates a task with a start and end time but with preset tags
     * @param all All Strings from the remainder of the input line after the command string.
     * @param tags All tags that the task has
     * @throws PukeException If an incorrect format is used.
     */
    public Event(String[] all, String[] tags) throws PukeException {
        super(EVENT_LABEL, all[0], tags);
        try {
            from = LocalDateTime.parse(all[1].split("from ")[1]);
            to = LocalDateTime.parse(all[2].split("to ")[1]);
        } catch (Exception DateTimeParseException) {
            throw new PukeException();
        }
    }

    /**
     * Creates an Event task using data stored in the ListData.txt file.
     *
     * @param desc Description of the event
     * @param from Start date and time of the event.
     * @param to Ending date and time of the event.
     * @return The Event task.
     * @throws PukeException If an incorrect format is detected e.g. the file is corrupted.
     */
    public static Event construct(String desc, String from, String to, String[] tags) throws PukeException {
        String[] container = new String[CONSTRUCT_SIZE];
        container[0] = desc;
        container[1] = "from " + from;
        container[2] = "to " + to;
        return new Event(container, tags);
    }

    /**
     * Returns a String representation of the Deadline task that is stored in the ListData.txt file.
     *
     * @return a String representation.
     */
    @Override
    public String write() {
        return String.format("%s/%s/%s%s", super.write(), from, to, super.writeTags());
    }

    /**
     * Returns a String representation of the Deadline task that is used for Displaying in the to do list.
     *
     * @return a String representation.
     */
    public String toString() {
        return String.format("%s (from: %s to: %s) %s", super.toString(), from, to, super.printTags());
    }
    @Override
    public boolean equals(Object other) {
        boolean isInstance = other instanceof Event;
        boolean isSameTask = toString().equals(other.toString());
        return isInstance && isSameTask;
    }
}
