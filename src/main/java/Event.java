public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, "event");
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean marked) {
        super(description, "event");
        this.from = from;
        this.to = to;
        this.mark(marked);
    }

    @Override
    public String getOriginalMessage() {
        return "event " + this.getDescription() + " /from " + this.from + " /to " + this.to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")" ;
    }
}
