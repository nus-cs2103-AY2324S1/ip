package Models;

import java.time.LocalDateTime;

import static LogicHandlers.Parsers.DateTimeParser.parseDateTimeForOutput;
import static LogicHandlers.Parsers.DateTimeParser.parseStringFromDateTime;

public class Event extends Task {
    LocalDateTime startTime;
    LocalDateTime endTime;
    public Event(String name, Boolean marked, LocalDateTime startTime, LocalDateTime endTime) {
        super(name, marked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskDetails() {
        return "Event," + this.name + "," + this.isMarked + ","
                + parseStringFromDateTime(this.startTime) + "," + parseStringFromDateTime(this.endTime);
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[E][X] " + this.name +
                    " (from: " + parseDateTimeForOutput(this.startTime) +
                    " to: " + parseDateTimeForOutput(this.endTime) + ")";
        }

        return "[E][ ] " + this.name +
                " (from: " + parseDateTimeForOutput(this.startTime) +
                " to: " + parseDateTimeForOutput(this.endTime) + ")";
    }
}
