package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private static final String TYPE = "[E]";
    protected LocalDate fromTime;
    protected LocalDate toTime;

    public Event(String task, String fromTime, String toTime) throws DateTimeParseException {
        super(task);
        this.fromTime = LocalDate.parse(fromTime);
        this.toTime = LocalDate.parse(toTime);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return (date.isAfter(this.fromTime) || date.isEqual(this.fromTime))
                && (date.isBefore(this.toTime) || date.isEqual(this.toTime));
    }

    @Override
    public String toSaveFormat() {
        return "Event | " + super.toSaveFormat() + " | " + this.fromTime + " | " + this.toTime;
    }

    @Override
    public String toString() {
        return Event.TYPE + super.toString() + " (from: "
                + this.fromTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.toTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
