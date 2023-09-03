package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline that the user wishes to keep track of.
 * A deadline is a type of task which has an end date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initialises an deadline object.
     *
     * @param description A short description of the deadline task
     * @param by the deadline date and time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        setBy(by);
    }

    /**
     * Retrieves the date and time of the deadline.
     *
     * @return Date and time of the deadline
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets the date and time of the deadline.
     *
     * @param by Date and time of the deadline
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Encodes the deadline object into a formatted string to be saved into a text file.
     *
     * @return Encoded string of Deadline object
     */
    @Override
    public String toSaveDataFormat() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getDescription(),
                getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    /**
     * Produces a string representation of a Deadline object.
     *
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                getBy().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")));
    }

    /**
     * Checks if another deadline is equivalent to the current event.
     *
     * @param other The other deadline object we are checking for equivalence
     * @return if the other deadline object is equivalent to the current object
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return (this.description.equals(otherDeadline.description))
                    && (this.by.equals(otherDeadline.by));
        } else {
            return false;
        }
    }
}