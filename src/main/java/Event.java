import java.util.regex.*;

public class Event extends Task {
    private final String from, to;

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    Event(String taskName, boolean isMarked, String from, String to) {
        super(taskName, isMarked);
        this.from = from;
        this.to = to;
    }
}
