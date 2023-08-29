package seedu.duke;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = super.parseStringToTime(from);
        this.to = super.parseStringToTime(to);
    }

    public String toStringForSave() {
        return "E" + " | " + super.toStringForSave() + " | "
                + super.convertTimeForSave(this.from) + " | "
                + super.convertTimeForSave(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + super.convertTimeToString(this.from)
                + " to: " + super.convertTimeToString(this.to) + ")";
    }
}
