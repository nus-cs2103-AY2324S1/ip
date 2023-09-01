public class Event extends Task { // inheritance
    protected String start; // start date/time
    protected String end; // end date/time

    public Event(String description, String start, String end) { // constructor
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + "|" + start + "-" + end;
    }
}
