package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s | to: %s)", super.toString(),
                this.from.format(DATE_TIME_FORMAT), this.to.format(DATE_TIME_FORMAT));
    }

    @Override
    public String encodeTask() {
        return String.format("E;%s;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.from, this.to);
    }
}
