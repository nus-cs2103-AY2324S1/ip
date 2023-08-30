package ekud.state;

import ekud.util.DateTime;

public final class EventTask extends Task {
    private final DateTime from;
    private final DateTime to;

    public EventTask(String title, boolean isDone, DateTime from, DateTime to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    public DateTime getFrom() {
        return from;
    }

    public DateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
