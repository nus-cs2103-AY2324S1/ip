package nobita.task;

import nobita.exception.NobitaException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringJoiner;

/**
 * Class that encapsulates Deadline which extends from Task.
 *
 * @author Zheng Chenglong
 */
public class Deadline extends Task {

    /** The data of the Deadline is due */
    private String by;

    /** The data of the Deadline is due */
    private LocalDate date;

    /** The time of the Deadline is due */
    private LocalTime time;

    /**
     * Constructs Deadline using name and due date of Deadline.
     *
     * @param taskName The name of the Deadline.
     * @param by The due date.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        String[] dueDateTime = by.split(" ");
        this.date = LocalDate.parse(dueDateTime[0]); // input format "2016-06-11"
//        this.time = LocalTime.parse(dueDateTime[1]); // input format "06:30"
    }

    /**
     * Return a String representation of Deadline.
     *
     * @return A String representing of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Return a String representation of Deadline that is formatted for file reading and writing.
     *
     * @return A String representation of Deadline that is formatted for file reading and writing.
     */
    @Override
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("D").add(super.toFileFormat()).add(this.by);
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
        } else if (updateField.equalsIgnoreCase("/by")) {
            this.by = updateValue;
        } else {
            throw new NobitaException("Deadline task do not have " + updateField + " field.");
        }
        return this;
    }
}
