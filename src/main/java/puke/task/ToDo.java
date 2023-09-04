package puke.task;

import puke.PukeException;

/**
 * A Task class that does not have a specific time attached to it.
 */
public class ToDo extends Task {
    private static final String tag = "[T]";

    /**
     * Creates a Task with no set deadline or time period.
     *
     * @param desc The description of the task
     * @throws PukeException If an incorrect format is used.
     */
    public ToDo(String desc) throws PukeException {
        super(tag, desc);
    }
}
