package duke.task;

import duke.exception.UnknownCommandException;

/**
 * The Event class extends Task. An event has 2 extra fields
 * of start and end time
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /** Constructor for Event */
    public Event(String done, String description, String start, String end) {
        super(description, done);
        this.start = start;
        this.end = end;
    }

    /**
     * Sets the description, start and end date.
     *
     * @param description the description of the task.
     * @param start the start time of the event.
     * @param end the end time of the event.
     */
    public Event(String commands) throws UnknownCommandException {
        String[] items = commands.split(" /");
        if (items.length == 3) {
            assert items.length == 3 : "Should only have 3 items in total";
            if (items[1].startsWith("from ") && items[2].startsWith("to ")) {
                this.description = items[0];
                this.start = items[1].substring(5);
                this.end = items[2].substring(3);
            } else {
                throw new UnknownCommandException();
            }
        } else {
            throw new UnknownCommandException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String showFileRepresentation() {
        return ("E" + " | " + (this.isDone ? "1" : "0")
                + " | " + this.description + " | " + start + " | " + end + "\n");
    }
}
