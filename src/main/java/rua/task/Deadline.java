package rua.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate due;

    /**
     * Constructs a Deadline object (assuming unmarked).
     *
     * @param description A String to describe the task.
     * @param due A LocalDate to represent the due date of the deadline.
     */
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description A String to describe the task.
     * @param due A LocalDate to represent the due date of the deadline.
     * @param isMarked A boolean to indicate whether it is marked.
     */
    public Deadline(String description, LocalDate due, Boolean isMarked) {
        super(description, isMarked);
        this.due = due;
    }

    /**
     * {@inheritDoc}
     * It returns "D" for Deadline type.
     *
     * @return The task type ("D" for Deadline type).
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * {@inheritDoc}
     *
     * @return A new Deadline task object with the same description but it is marked.
     */
    @Override
    public Deadline setMarked() {
        return new Deadline(this.description, this.due, true);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new Deadline task object with the same description but it is unmarked.
     */
    @Override
    public Deadline setUnmarked() {
        return new Deadline(this.description, this.due, false);
    }

    /**
     * {@inheritDoc}
     *
     * @param date A given date which we will check whether this task happens on that date.
     * @return A boolean to indicate whether it happens on that day.
     */
    @Override
    public Boolean isHappeningOnThatDate(LocalDate date) {
        return date.isEqual(due);
    }

    /**
     * Returns the due date of this event.
     *
     * @return The due date of this event.
     */
    public String getDue() {
        return due.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    /**
     * Compares the task with other objects and return true if they are the same Deadline task.
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline c = (Deadline) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description) &&
                c.isMarked.equals(this.isMarked) &&
                c.due.isEqual(this.due);
    }

    /**
     * Returns a string to represent this Deadline task.
     *
     * @return A string representing this Deadline task in the format:
     * [D][ marked indicator ] description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                due.format(DateTimeFormatter.ofPattern("MM dd yyyy"))  + ")";
    }
}
