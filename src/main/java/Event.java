public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from =  from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " " + "to: " + to + ")";
    }
}
