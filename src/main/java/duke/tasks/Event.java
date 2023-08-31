package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDateTime;

/**
 * duke.tasks.Event class representing a task and description.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the duke.tasks.Event class.
     *
     * @param description Description of the task.
     * @param from start time of task.
     * @param to end time of task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * String representation of the duke.tasks.Event task.
     *
     * @return Information of event.
     */
    @Override
    public String toString() {
        String ret = "[E] " + super.toString() + " (from: " + printDateTime(this.from) + " to: "
                + printDateTime(this.to) + ")" ;
        return ret;
    }
}
