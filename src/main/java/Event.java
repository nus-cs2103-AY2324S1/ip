public class Event extends TaskWithPeriod {
    public Event(String description, String from, String to) {
        super(description, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getFrom(), this.getEnd());
    }

    @Override
    public String toSave() {
        return String.format("E|%s|%s|%s|%s", this.getStatusIcon(), this.getDescription(), this.getFromSave(), this.getEndSave());
    }
}
