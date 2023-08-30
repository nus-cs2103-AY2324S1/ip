package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime to;
    private final LocalDateTime from;

    public Event(String name, String from, String to) {
        super(name);
        this.from = DateParser.convertStringToDateTime(from);
        this.to = DateParser.convertStringToDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + DateParser.convertDateTimeToString(this.from) + " to: " + DateParser.convertDateTimeToString(this.to) + ")";
    }

    public String convertTaskToString() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + super.getName() + " | " + DateParser.convertDateTimeToString(this.from) + " | " + DateParser.convertDateTimeToString(this.to);
    }
}
