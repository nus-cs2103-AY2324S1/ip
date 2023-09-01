package blip.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime eventFrom;
    protected LocalDateTime eventTo;

    public Event(String description, LocalDateTime eventFrom, LocalDateTime eventTo, boolean isDone) {
        super(description, isDone);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    @Override
    public String saveToFileString(){
        return "E " + (super.isDone ? "| 1 | " : "| 0 | ") + super.toString() + " | "
                + this.eventFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | "
                + this.eventTo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon()  + super.toString() + " (from: "
                + this.eventFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + " to: "
                + this.eventTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + ")";
    }
}
