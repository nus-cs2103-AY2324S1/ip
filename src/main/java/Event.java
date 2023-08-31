import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;

    Event(String name, String timeStart, String timeEnd) throws DateTimeParseException {
        super(name);
        try {
            this.timeStart = DPUtils.dPTryParseDateTime(timeStart);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for 'timeStart'. Please provide a valid date.", e);
        }

        try {
            this.timeEnd = DPUtils.dPTryParseDateTime(timeEnd);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for 'timeEnd'. Please provide a valid date.", e);
        }
        //add a catch for if timeStart is after timeEnd
        if (this.timeStart.isAfter(this.timeEnd)) {
            throw new IllegalArgumentException("'timeStart' cannot be after 'timeEnd'.");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), DPUtils.dPFormatDateTime(this.timeStart), DPUtils.dPFormatDateTime(this.timeEnd));
    }
}
