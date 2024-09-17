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
            String[] splits = specifications.split(" ", 2);
            String updateType = splits[0];
            String description = splits.length > 1 ? splits[1] : "";
            switch (updateType) {
            case "/description":
                validateInput(description);
                updateDescription(description);
                break;
            case "/from":
                validateInput(description);
                updateStart(description);
                break;
            case "/to":
                validateInput(description);
                updateEnd(description);
                break;
            default:
                throw new IllegalArgumentException("OOPS!!! Update of a deadline task description must have "
                        + "/description <description>. "
                        + "Update of a deadline task deadline must have /by <date>. ");
            }
            return "Ok, I've updated the event task to the following:\n" + this;
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new IllegalArgumentException(
                    "OOPS!!! Please add a description.");
        }
    }

    private void validateInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! Please add a description.");
        }
        if (input.contains("/description")
                || input.contains("/from")
                || input.contains("/to")) {
            throw new IllegalArgumentException(
                    "OOPS!!! Only 1 attribute can be updated at one time.");
        }
    }

    private void updateDescription(String description) {
        this.description = description;
    }

    private void updateStart(String input) {
        LocalDate date = DateParser.parseDate(input);
        this.start = date;
    }

    private void updateEnd(String input) {
        LocalDate date = DateParser.parseDate(input);
        this.end = date;
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
