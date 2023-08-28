public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileFormat() {
        return String.format("E%s%s%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.from,
                Storage.SEPARATOR, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return super.equals(event) && this.from.equals(event.from) && this.to.equals(event.to);
        }
        return false;
    }
}
