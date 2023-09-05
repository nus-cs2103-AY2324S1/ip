package seedu.duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;

import seedu.duke.exceptions.InvalidEventException;

/**
 * A child class of duke.Tasks, it represents tasks with a start time & end time.
 */

public class Event extends Task {


    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor to create an Event object which represents a task with start & end times.
     * @param description the Event's description
     * @param start the start date of the description
     * @param end the end date of the description
     * @throws InvalidEventException thrown when the start & end date is not in the correct format.
     */
    public Event(String description, String start, String end) throws InvalidEventException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeException e) {
            throw new InvalidEventException("Invalid date");
        }

    }

    /**
     * Constructor to create an Event object which represents a task with a start and end time.
     * This constructor can set isDone of the object and is used to load the
     * task list after Lemon is started.
     * @param description the task description
     * @param start the start time of the event
     * @param end the end time of the event
     * @param isDone boolean representing whether the task is completed
     * @throws InvalidEventException if the command does not have a start, end time
     */
    public Event(String description, String start, String end, boolean isDone) throws InvalidEventException {
        super(description, isDone);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeException e) {
            throw new InvalidEventException("Invalid date");
        }
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String addDataFormat() {
        return "E " + super.addDataFormat() + " | " + start + " | " + end;
    }
}
