package chatty;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        //convert String to LocalDateTime type
        try {
            this.by = DateTimeParser.toLocalDateTimeFromWrongFormat(by);
        } catch (DateTimeParseException e1) {
            // If parsing in the first format fails, try the second format
            this.by = DateTimeParser.toLocalDateTimeFromCorrectFormat(by);
        }
    }

    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description);
        try {
            this.by = DateTimeParser.toLocalDateTimeFromWrongFormat(by);
        } catch (DateTimeParseException e1) {
            // If parsing in the first format fails, try the second format
            this.by = DateTimeParser.toLocalDateTimeFromCorrectFormat(by);
        }
        this.isDone = isDone;
    }

    @Override
    public String parse() {
        return "D | " + this.description + " | " + this.isDone + " | " + DateTimeParser.fromLocalDateTimeToString(this.by);
    }
    @Override
    public String toString() {
        return  "[D]" + super.toString() + " (by: " + DateTimeParser.fromLocalDateTimeToString(this.by) + ")";
    }
}
