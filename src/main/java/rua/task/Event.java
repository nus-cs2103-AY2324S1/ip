package rua.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an Event object (assuming unmarked).
     *
     * @param description A String to describe the task.
     * @param from A LocalDate to represent the event starting time.
     * @param to A LocalDate to represent the event ending time.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object.
     *
     * @param description A String to describe the task.
     * @param from A LocalDate to represent the event starting time.
     * @param to A LocalDate to represent the event ending time.
     * @param isMarked A boolean to indicate whether it is marked.
     */
    public Event(String description, LocalDate from, LocalDate to, Boolean isMarked) {
        super(description, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     * It returns "E" for Event type.
     *
     * @return The task type ("E" for Event type).
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the starting time of this event.
     *
     * @return The starting time of this event.
     */
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    /**
     * Returns the ending time of this event.
     *
     * @return The ending time of this event.
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    /**
     * {@inheritDoc}
     *
     * @return A new Event task object with the same description but it is marked.
     */
    @Override
    public Event setMarked() {
        return new Event(this.description, this.from, this.to, true);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new Event task object with the same description but it is unmarked.
     */
    @Override
    public Event setUnmarked() {
        return new Event(this.description, this.from, this.to, false);
    }

    /**
     * {@inheritDoc}
     *
     * @param date A given date which we will check whether this task happens on that date.
     * @return A boolean to indicate whether it happens on that day.
     */
    @Override
    public Boolean isHappeningOnThatDate(LocalDate date) {
        return (date.isEqual(from) || date.isAfter(from)) &&
                (date.isEqual(to) || date.isBefore(to));
    }

    /**
     * Compares the task with other objects and return true if they are the same Event task.
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }
        Event c = (Event) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description) &&
                c.isMarked.equals(this.isMarked) &&
                c.from.isEqual(this.from) &&
                c.to.isEqual(this.to);
    }

    /**
     * Returns a string to represent this Event task.
     *
     * @return A string representing this Event task in the format:
     * [E][ marked indicator ] description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MM dd yyyy")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MM dd yyyy")) +
                ")";
    }
}
