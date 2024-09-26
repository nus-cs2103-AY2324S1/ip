package seedu.duke;
import java.time.LocalDate;

/**
 * Todo models the todo Tasks by inheriting from the parent Task class.
 *
 * @param description is the name of the todo tasks that needs to be done.
 */
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Deadline models the deadline Tasks by inheriting from the parent Task class.
 *
 * @param description is the name of the todo tasks that needs to be done.
 * @param by is the date that the deadline needs to be done.
 */
class Deadline extends Task {
    public LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        LocalDate date = LocalDate.parse(by);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}


/**
 * Represents an event task, which has a specific start and end date.
 *
 * @param description A brief description of the event task.
 * @param from The start date of the event task.
 * @param to The end date of the event task.
 */

class Event extends Task {
    public LocalDate from;
    public LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        this.from = fromDate;
        this.to = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

