/**
 * The Event class extends Task. An event has 2 extra fields
 * of start and end time
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Event extends Task{
    protected String start;
    protected String end;

    /** Constructor for Event */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
