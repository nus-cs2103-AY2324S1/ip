package puke.task;

import java.time.LocalDateTime;

import puke.managers.PukeException;

/**
 * A Task class that has a start and end time attached to it.
 */
public class Event extends Task {
    private static final String tag = "[E]";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a task with a start and end time
     *
     * @param all All Strings from the remainder of the input line after the command string.
     * @throws PukeException If an incorrect format is used.
     */
    public Event(String[] all) throws PukeException {
        super(tag, all[0]);
        try {
            this.from = LocalDateTime.parse(all[1].split("from ")[1]);
            this.to = LocalDateTime.parse(all[2].split("to ")[1]);
        } catch (Exception DateTimeParseException) {
            System.out.println(all[1].split("from ")[1]);
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
    public static Event construct(String desc, String from, String to) throws PukeException {
        String[] container = new String[3];
        container[0] = desc;
        container[1] = "from " + from;
        container[2] = "to " + to;
        return new Event(container);
    }

    /**
     * Returns a String representation of the Deadline task that is stored in the ListData.txt file.
     *
     * @return a String representation.
     */
    @Override
    public String write() {
        return super.write() + "/" + this.from + "/"
                + this.to;
    }

    /**
     * Returns a String representation of the Deadline task that is used for Displaying in the to do list.
     *
     * @return a String representation.
     */
    public String toString() {
        return super.toString()
                + " (from: "
                + this.from
                + " "
                + "to: "
                + this.to
                + ")";
    }
}
