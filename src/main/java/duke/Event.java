package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDateTime start;
    LocalDateTime end;

    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public static String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

        return dt.format(dateTimeFormatter);
    }

    @Override
    public String toSavedString() {
        return String.format("[E] %s//%s//%s//", super.toSavedString(), formatDateTime(this.start),
                formatDateTime(this.end));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), formatDateTime(this.start),
                formatDateTime(this.end));
    }
}
