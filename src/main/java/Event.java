public class Event extends Task{
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    Event(String description, String from, String to, Boolean marked) {
        super(description, marked);
        this.from = from;
        this.to = to;
    }

    @Override
    Event mark() {
        return new Event(this.description, this.from, this.to, true);
    }

    @Override
    Event unmark() {
        return new Event(this.description, this.from, this.to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " +
                this.to + ")";
    }
}
