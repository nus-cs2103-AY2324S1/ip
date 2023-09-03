import java.util.Date;

public class Event extends Task {
    // fields
    protected String start;
    protected String end;

    // toString


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    // Constructor
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
}
