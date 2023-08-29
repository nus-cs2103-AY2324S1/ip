public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, Type.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {

        return "[E]" + "[" + getStatusIcon() + "]" + description + "(" + from + to + ")";
    }
}
