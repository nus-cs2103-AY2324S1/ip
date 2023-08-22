public class Event extends Task{
    private String to;
    private String from;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.from + " to: " + this.to + ")";
    }
}
