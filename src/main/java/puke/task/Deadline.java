package puke.task;

import java.time.LocalDateTime;

import puke.managers.PukeException;

/**
 * A Task class that has a deadline attached to it.
 */
public class Deadline extends Task {
    private static final String DEADLINE_LABEL = "[D]";
    private static final int CONSTRUCT_SIZE = 2;
    private final LocalDateTime date;

    /**
     * Creates a Task with a set deadline.
     *
     * @param all All strings from the remainder of the input after being split
     * @throws PukeException If an incorrect format is used.
     */
    public Deadline(String[] all) throws PukeException {
        super(DEADLINE_LABEL, all[0]);
        try {
            date = LocalDateTime.parse(all[1].split("by ")[1]);
        } catch (Exception DateTimeParseException) {
            throw new PukeException();
        }
    }

    /**
     * Creates a Task with a set deadline but with preset tags
     * @param all All strings from the remainder of the input after being split
     * @param tags All tags that the task has
     * @throws PukeException If an incorrect format is used.
     */
    public Deadline(String[] all, String[] tags) throws PukeException {
        super(DEADLINE_LABEL, all[0], tags);
        try {
            date = LocalDateTime.parse(all[1].split("by ")[1]);
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
    public static Deadline construct(String desc, String date, String[] tags) throws PukeException {
        String[] container = new String[CONSTRUCT_SIZE];
        container[0] = desc;
        container[1] = "by " + date;
        return new Deadline(container, tags);
    }

    /**
     * Returns a String representation of the Deadline task that is stored in the ListData.txt file.
     *
     * @return a String representation.
     */
    @Override
    public String write() {
        return String.format("%s/%s%s", super.write(), date, super.writeTags());
    }

    /**
     * Returns a String representation of the Deadline task that is used for Displaying in the to do list.
     *
     * @return a String representation
     */
    public String toString() {
        return String.format("%s (by: %s) %s", super.toString(), date, super.printTags());
    }
    @Override
    public boolean equals(Object other) {
        boolean isInstance = other instanceof Deadline;
        boolean isSameTask = toString().equals(other.toString());
        return isInstance && isSameTask;
    }
}
