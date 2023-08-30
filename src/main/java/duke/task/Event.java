package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String type = "[E]";
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|from %s to %s", this.done ? 1 : 0, this.name
                , from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                , to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return type + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) +  ")";
    }
}
