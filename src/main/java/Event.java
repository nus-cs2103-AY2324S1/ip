/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class Event extends Task {
    /** A string indicating start date / time of task */
    protected String start;
    /** A string indicating end date / time of task */
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
