package seedu.duke;
import java.time.LocalDate;
import seedu.duke.Task;

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

class Event extends Task {
    public String from;
    public String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

