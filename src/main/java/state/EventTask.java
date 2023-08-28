package state;

import java.time.LocalDateTime;

public final class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventTask(String title, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
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
