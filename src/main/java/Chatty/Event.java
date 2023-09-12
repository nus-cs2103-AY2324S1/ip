package chatty;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        //assumes from and to is either both in wrong format, or both in correct format
        try {
            this.from = DateTimeParser.toLocalDateTimeFromWrongFormat(from);
            this.to = DateTimeParser.toLocalDateTimeFromWrongFormat(to);
        } catch (DateTimeParseException e1) {
            // If parsing in the first format fails, try the second format
            this.from = DateTimeParser.toLocalDateTimeFromCorrectFormat(from);
            this.to = DateTimeParser.toLocalDateTimeFromCorrectFormat(to);
        }
    }

    public Event(String description, String from, String to, boolean isDone) throws DateTimeParseException {
        super(description);
        //assumes from and to is either both in wrong format, or both in correct format
        try {
            this.from = DateTimeParser.toLocalDateTimeFromWrongFormat(from);
            this.to = DateTimeParser.toLocalDateTimeFromWrongFormat(to);
        } catch (DateTimeParseException e1) {
            // If parsing in the first format fails, try the second format
            this.from = DateTimeParser.toLocalDateTimeFromCorrectFormat(from);
            this.to = DateTimeParser.toLocalDateTimeFromCorrectFormat(to);
        }
        this.isDone = isDone;
    }

    @Override
    public String parse() {
        return "E | " + this.description + " | " + this.isDone + " | " + DateTimeParser.fromLocalDateTimeToString(this.from) + " | " + DateTimeParser.fromLocalDateTimeToString(this.to);
    }
    @Override
    public String toString() {
        return  "[E]" + super.toString() + " (from: " + DateTimeParser.fromLocalDateTimeToString(this.from) + " to: " + DateTimeParser.fromLocalDateTimeToString(this.to) + ")";
    }
}
