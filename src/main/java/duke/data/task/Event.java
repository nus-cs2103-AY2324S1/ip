package duke.data.task;

/**
 * Represents a task with start and end time.
 */
public class Event extends Task {
    private From from;
    private To to;

    public void setFrom(String from) {
        this.from = new From(from);
    }

    public void setTo(String to) {
        this.to = new To(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
