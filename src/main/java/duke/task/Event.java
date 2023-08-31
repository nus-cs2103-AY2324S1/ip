package duke.task;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime fromDateAndTime;
    public LocalDateTime toDateAndTime;

    public Event(String description, LocalDateTime fromDateAndTime, LocalDateTime toDateAndTime) {
        super(description, Type.EVENT);
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
    }

    @Override
    public String toString() {
        // example Oct 15 2019 2pm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ha");
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + fromDateAndTime.format(formatter) +
                " to: " + toDateAndTime.format(formatter) + ")";
    }
}
