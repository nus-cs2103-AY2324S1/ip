package nobita.task;

import nobita.exception.NobitaException;

import java.util.StringJoiner;

/**
 * Class that encapsulates Event which extends from Task.
 *
 * @author Zheng Chenglong
 */
public class Event extends Task {

    /** The start time of the Event */
    protected String start;

    /** The end time of the Event */
    protected String end;

    /**
     * Constructs Deadline using name and due date of Event.
     *
     * @param taskName The name of the Event.
     * @param start The start date of the Event.
     * @param end The end date of the Event.
     */
    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Return a String representation of Event.
     *
     * @return A String representing of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: "+ this.end +  ")";
    }

    /**
     * Return a String representation of Event that is formatted for file reading and writing.
     *
     * @return A String representation of Event that is formatted for file reading and writing.
     */
    @Override
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("E").add(super.toFileFormat()).add(this.start).add(this.end);
        return joiner.toString();
    }

    /**
     * Update a field of the Deadline.
     *
     * @param updateField The field of Deadline to be updated.
     * @param updateValue The value of the field to be updated to.
     * @return The updated Deadline.
     * @throws NobitaException If specified update field does not exist.
     */
    public Task setField(String updateField, String updateValue) throws NobitaException {
        if (updateField.equalsIgnoreCase("name")) {
            return super.setField(updateField, updateValue);
        } else if (updateField.equalsIgnoreCase("/from")) {
            this.start = updateValue;
        } else if (updateField.equalsIgnoreCase("/to")) {
            this.end = updateValue;
        } else {
            throw new NobitaException("Event task do not have " + updateField + " field.");
        }
        return this;
    }
}
