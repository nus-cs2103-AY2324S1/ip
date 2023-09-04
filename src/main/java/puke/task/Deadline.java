package puke.task;

import java.time.LocalDateTime;

import puke.PukeException;

/**
 * A Task class that has a deadline attached to it.
 */
public class Deadline extends Task {
    private static final String tag = "[D]";
    private final LocalDateTime date;

    /**
     * Creates a Task with a set deadline.
     *
     * @param all All strings from the remainder of the input after being split
     * @throws PukeException If an incorrect format is used.
     */
    public Deadline(String[] all) throws PukeException {
        super(tag, all[0]);
        try {
            this.date = LocalDateTime.parse(all[1].split("by ")[1]);
        } catch (Exception DateTimeParseException) {
            throw new PukeException();
        }
    }

    /**
     * Creates a Deadline Task using input from the ListData.txt file.
     *
     * @param desc The description of the task.
     * @param date The date of the deadline.
     * @return The Deadline task.
     * @throws PukeException If an incorrect format is detected e.g. the file is corrupted.
     */
    public static Deadline construct(String desc, String date) throws PukeException {
        String[] container = new String[2];
        container[0] = desc;
        container[1] = "by " + date;
        return new Deadline(container);
    }

    /**
     * Returns a String representation of the Deadline task that is stored in the ListData.txt file.
     *
     * @return a String representation.
     */
    @Override
    public String write() {
        return super.write() + "/" + this.date;
    }

    /**
     * Returns a String representation of the Deadline task that is used for Displaying in the to do list.
     *
     * @return a String representation
     */
    public String toString() {
        return super.toString() + " (by: " + this.date + ")";
    }
}
