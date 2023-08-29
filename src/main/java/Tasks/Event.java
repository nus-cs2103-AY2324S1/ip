package Tasks;

/**
 * Represents an event task in the Duke application.
 * This class extends the base class Task and includes the start and end dates of the event.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs a new Event object with a title, start date, and end date.
     *
     * @param title  The title of the event task.
     * @param from   The start date of the event.
     * @param to     The end date of the event.
     */
    public Event(String title, String from, String to, boolean isMarked) {
        super(title, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * Generates a string representation of the Event object.
     *
     * @return A formatted string indicating the status, title, start date, and end date of the event.
     */
    @Override
    public String toString() {
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[E]" + mark + title + " (from: " + this.from + " to: " + this.to + ")";
    }
    @Override
    public String toSave() {
        String res = "E";
        res += (isMarked ? "| 1" : "| 0");
        res += "| " + title;
        res += "| " + from;
        res += "| " + to;
        return res;
    }
}
