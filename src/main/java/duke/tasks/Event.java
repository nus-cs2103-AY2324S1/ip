package duke.tasks;

import java.time.LocalDate;

/**
 * Adapted from Partial Solution given in https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html under Level-4
 * Event class extends from the parent class Task
 * Events: tasks that start at a specific date/time and ends at a specific date/time 
 * e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019
 */
public class Event extends Task {

    /**
     * The start date/time of the event.
     */
    protected LocalDate from;
    
    /**
     * The end date/time of the event.
     */
    protected LocalDate to;

    /**
     * Creates an Event object.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event object.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     * @param isDone Whether the event is done.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DATE_TIME_OUTPUT) + " to: " + to.format(DATE_TIME_OUTPUT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
