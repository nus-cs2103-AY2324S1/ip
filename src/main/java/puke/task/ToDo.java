package puke.task;

import puke.managers.PukeException;

/**
 * A Task class that does not have a specific time attached to it.
 */
public class ToDo extends Task {
    private static final String TODO_LABEL = "[T]";

    /**
     * Creates a Task with no set deadline or time period.
     *
     * @param desc The description of the task
     * @throws PukeException If an incorrect format is used.
     */
    public ToDo(String desc) throws PukeException {
        super(TODO_LABEL, desc);
        if (desc.isEmpty()) {
            throw new PukeException();
        }
    }

    /**
     * Creates a Task with no set deadline or time period but has preset tags
     * @param desc The description of the task
     * @param tags All preset tags
     * @throws PukeException If an incorrect format is used.
     */
    public ToDo(String desc, String[] tags) throws PukeException {
        super(TODO_LABEL, desc, tags);
    }

    /**
     * Constructs a to do object from data from ListData.txt
     * @param desc The task description
     * @param tags The tags that the task has
     * @return The to do object
     * @throws PukeException if an incorrect format is used.
     */
    public static ToDo construct(String desc, String[] tags) throws PukeException {
        return new ToDo(desc, tags);
    }

    /**
     * Returns the string representation of a to do task to be stored in the ListData.txt file
     * @return the string representation.
     */
    @Override
    public String write() {
        return super.write() + super.writeTags();
    }
    /**
     * Returns the string representation of this to do task
     * @return the String representation
     */
    public String toString() {
        return String.format("%s %s", super.toString(), super.printTags());
    }
    @Override
    public boolean equals(Object other) {
        boolean isInstance = other instanceof ToDo;
        boolean isSameTask = toString().equals(other.toString());
        return isInstance && isSameTask;
    }
}
