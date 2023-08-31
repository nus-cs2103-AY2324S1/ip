package cloud.main.todo;



/**
 * Represents an event TODO, which has both a starting and ending timestamp.
 */
public class Event extends Deadline {
    private String timestampStart;

    public Event(String _description, String start, String end) {
        super(_description, end);

        this.timestampStart = start;
    }

    public String getStart() {
        return this.timestampStart;
    }
}
