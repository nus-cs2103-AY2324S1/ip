package chatter.task;

import java.time.LocalDate;

/**
 * Represents a task that has a start and end time.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Event extends Task {
    /** A string indicating start date / time of task */
    protected String altStart;
    /** A localDate indicating start date / time of task */
    protected LocalDate start;
    /** A string indicating end date / time of task */
    protected String altEnd;
    /** A localDate indicating end date / time of task */
    protected LocalDate end;

    /**
     * A constructor to initialize the chatter.task.Event class.
     *
     * @param description Description of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public Event(String description, String start, String end) {
        super(description);

        try {
            this.start = LocalDate.parse(start);
            this.altStart = "";
        } catch (Exception e) {
            this.altStart = start;
        }

        try {
            this.end = LocalDate.parse(end);
            this.altEnd = "";
        } catch (Exception e) {
            this.altEnd = end;
        }
    }

    /**
     * Returns the string representation of the chatter.task.Event that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the chatter.task.Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.altStart + convertDateToString(this.start)
                + " to: " + this.altEnd + convertDateToString(this.end) + ")";
    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public String toStorageString() {
        return "E, " + this.isDone + ", " + this.description + ", " + this.altStart
                + convertDateToStorageString(this.start) + ", " + this.altEnd + convertDateToStorageString(this.end);
    }
}
