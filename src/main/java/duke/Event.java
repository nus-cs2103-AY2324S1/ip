package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;

    private final LocalDateTime to;

    public Event(String eventName, LocalDateTime from, LocalDateTime to) {
        super(eventName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format(
                "[E]%s (from: %s, to: %s)",
                super.toString(),
                this.from.format(formatter),
                this.to.format(formatter)
        );
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format(
                "event %s /from %s /to %s%s",
                getTaskName(),
                this.from.format(formatter),
                this.to.format(formatter),
                (this.isDone() ? " /done" : "")
        );
    }
}
