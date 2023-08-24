public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {

        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {

        String result = "[E]" + super.toString() + "(from: " + this.start + "to: " + this.end + ")";
        return result;
    }
}
