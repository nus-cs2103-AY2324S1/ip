package rua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a Event Task.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");

    /**
     * Constructs an Event object (assuming unmarked).
     *
     * @param description A String to describe the task.
     * @param from A LocalDateTime to represent the event starting time.
     * @param to A LocalDateTime to represent the event ending time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from.isBefore(to) || from.isEqual(to)
                : "The first date should not be later than the second date";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object.
     *
     * @param description A String to describe the task.
     * @param from A LocalDateTime to represent the event starting time.
     * @param to A LocalDateTime to represent the event ending time.
     * @param isMarked A boolean to indicate whether it is marked.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isMarked) {
        super(description, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task object.
     *
     * @param description A String to describe the task.
     * @param from A LocalDateTime to represent the event starting time.
     * @param to A LocalDateTime to represent the event ending time.
     * @param isMarked A boolean to indicate whether it is marked.
     * @param tags An arraylist of tags.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isMarked, ArrayList<String> tags) {
        super(description, isMarked, tags);
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
        return this.from.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns the ending time of this event.
     *
     * @return The ending time of this event.
     */
    public String getTo() {
        return this.to.format(OUTPUT_FORMATTER);
    }

    /**
     * {@inheritDoc}
     *
     * @param date A given date which we will check whether this task happens on that date.
     * @return A boolean to indicate whether it happens on that day.
     */
    @Override
    public Boolean isHappeningOnThatDate(LocalDate date) {
        LocalDate fromDate = from.toLocalDate();
        LocalDate toDate = to.toLocalDate();
        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                && (date.isEqual(toDate) || date.isBefore(toDate));
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
        return c.description.equals(this.description)
                && c.isMarked.equals(this.isMarked)
                && c.from.isEqual(this.from)
                && c.to.isEqual(this.to);
    }

    /**
     * Returns a string to represent this Event task.
     *
     * @return A string representing this Event task in the format: [E][ marked indicator ] description.
     */
    @Override
    public String toString() {
        final String fromDateString = this.getFrom();
        final String toDateString = this.getTo();
        return "[E]" + super.toString() + " (from: "
                + fromDateString
                + " to: " + toDateString
                + ")";
    }
}
