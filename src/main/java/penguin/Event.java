package penguin;

public class Event extends Task {
    String from;
    String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String getDisplay() {
        return "[E]" + super.getDisplay() + "from " + this.from + " to " + this.to;
    }
}
