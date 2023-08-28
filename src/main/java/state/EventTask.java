package state;

public final class EventTask extends Task {
    private final String from;
    private final String to;

    public EventTask(String title, boolean isDone, String from, String to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public EventTask mark() {
        return new EventTask(getTitle(), true, from, to);
    }

    @Override
    public EventTask unmark() {
        return new EventTask(getTitle(), false, from, to);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
