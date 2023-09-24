package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * The Event class represents an event task with a specific start and end date.
 * It is a subclass of the Task class.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in string format (e.g., "yyyy-MM-dd").
     * @param to The end date of the event in string format (e.g., "yyyy-MM-dd").
     * @throws DukeException If there is an issue while parsing the dates or constructing the task.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    /**
     * Constructs an Event instance with data from storage.
     *
     * @param data Data representation of the task to construct.
     * @return The instance of task constructed.
     */
    public static Event constructWithData(String data) throws DukeException {
        int firstSplitIndex = data.indexOf("|");
        int secondSplitIndex = data.indexOf("|", firstSplitIndex + 1);
        int thirdSplitIndex = data.indexOf("|", secondSplitIndex + 1);
        boolean isDone = data.substring(0, firstSplitIndex - 1).equals("1");
        String desc = data.substring(firstSplitIndex + 2, secondSplitIndex - 1);
        String from = data.substring(secondSplitIndex + 2, thirdSplitIndex - 1);
        String to = data.substring(thirdSplitIndex + 2);
        Event event = new Event(desc, from, to);
        if (isDone) {
            event.markDone();
        }
        return event;
    }

    @Override
    public void updateDetails(String field, String details) throws DukeException {
        if (field.equals("desc")) {
            this.description = details;
        } else if (field.equals("from")) {
            LocalDate newDate = parseDate(details);
            this.from = newDate;
        } else if (field.equals("to")) {
            LocalDate newDate = parseDate(details);
            this.to = newDate;
        } else {
            throw new DukeException("OOPS!!! Only /desc /from /to flags allowed.");
        }
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "E | " + done + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
