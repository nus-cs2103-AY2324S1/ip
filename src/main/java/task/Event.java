package task;

import task.taskdatetime.TaskDateTime;

/**
 * Class encapsulating an Event, a task with a start and an end.
 */
public class Event extends Task {

    /**
     * String to put at the front of string rep of this, represents the name,
     * E for [E]vent
     */
    private static final String PREPEND = "[E]";

    /**
     * String representing whene this Event starts
     */
    private TaskDateTime startTime;

    /**
     * String representing when this Event ends
     */
    private TaskDateTime endTime;

    /**
     * Constructor for Event, setting name, starting time, ending time
     * @param eventName the name of this Deadline
     * @param startTime the starting time of this Deadline
     * @param endTime the ending time of this Deadline
     */
    public Event(String eventName, String startTime, String endTime) {
        super(eventName, false);
        this.startTime = TaskDateTime.fromDateTimeString(startTime);
        this.endTime = TaskDateTime.fromDateTimeString(endTime);
    }

    /**
     * The user-facing string representation of this Event, containing information about
     * what kind of task this is(an Event), its name, when it starts, when it ends, and whether it is done.
     * @return the user-facing string representation of this Deadline.
     */
    @Override
    public String toPrintString() {
        String postpend = String.format(
                " from : %s to: %s",
                this.startTime.getDateTimeAsString(), this.endTime.getDateTimeAsString()
        ); //need a whitespace here for formatting reasons
        return Event.PREPEND + super.toPrintString() + postpend;
    }
}
