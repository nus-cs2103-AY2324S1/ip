package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.parser.DateParser;

/** Abstraction for task that have start and end dates. */
public class Event extends Task {

    /** Start time of event. */
    protected LocalDate start;
    /** End time of event. */
    protected LocalDate end;

    /**
     * Creates a new Event object.
     *
     * @param description What the event is about.
     * @param isDone Whether the event is done or not.
     * @param start Start date of the event.
     * @param end End date of the event.
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "E " + isCompleted + " " + this.description
                + "/from" + this.start + "/to" + this.end;
    }

    @Override
    public String update(String specifications) {
        try {
            String[] splits = specifications.split(" ", 3);
            validateInput(splits);
            if (splits[0].matches("/description")) {
                String newDescription = splits[1];
                this.description = newDescription;
            }
            if (splits[0].matches("/from")) {
                LocalDate newStart = DateParser.parseDate(splits[1]);
                this.start = newStart;
            }
            if (splits[0].matches("/to")) {
                LocalDate newEnd = DateParser.parseDate(splits[1]);
                this.end = newEnd;
            }
            return "Ok, I've updated the event task to the following:\n" + this;
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new IllegalArgumentException(
                    "OOPS!!! Please add a description.");
        }
    }

    private void validateInput(String[] input) {
        if (input.length > 2) {
            throw new IllegalArgumentException(
                    "OOPS!!! Only 1 attribute can be updated at one time.");
        }
        if (!input[0].matches("/description")
                && !input[0].matches("/from")
                && !input[0].matches("/to")) {
            throw new IllegalArgumentException("OOPS!!! Update of an event task description must have "
                    + "/description <description>. "
                    + "Update of an event task start must have /from <date>. "
                    + "Update of an event task end must have /to <date>.");
        }
    }

    /**
     * Display string representation of an event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
