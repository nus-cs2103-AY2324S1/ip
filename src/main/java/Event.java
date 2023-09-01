public class Event extends Task {

    String start;
    String end;

    public Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSavedString() {
        return String.format("[E] %s//%s//%s//", super.toSavedString(), this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
