// Class representation of an event which has both a start and end time
public class Event extends Task{

    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (from: " + this.start + " to: " + this.end +  ")";
    }
}
