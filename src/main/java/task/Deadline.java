package task;

import task.taskdatetime.TaskDateTime;

/**
 * Encapsulates a task with a singular deadline,
 * so has a name, completion status, and a time representing the deadline
 */
public class Deadline extends Task {

    /**
     * String to put at the front of string rep of this, represents the name,
     * D for [D]eadline
     */
    private static final String PREPEND = "[D]";

    /**
     * String representing the deadline of this Deadline
     */
    private TaskDateTime deadlineTime;

    /**
     * Constructor for Deadline, setting name and time
     * @param deadlineName the name of this Deadline
     * @param deadlineTime the time of this Deadline
     */
    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName, false);
        this.deadlineTime = TaskDateTime.fromDateTimeString(deadlineTime);
    }

    /**
     * The user-facing string representation of this Deadline, containing information about
     * what kind of task this is(a Deadline), its name, when it is due, and whether it is done.
     * @return the user-facing string representation of this Deadline.
     */
    @Override
    public String toPrintString() {
        String postpend = String.format("(by : %s)",
                this.deadlineTime.getDateTimeAsString());
        return Deadline.PREPEND + super.toPrintString() + postpend;

    }
}
