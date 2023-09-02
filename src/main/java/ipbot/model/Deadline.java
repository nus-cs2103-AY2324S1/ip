package ipbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDateTime endTime;

    public Deadline(String description, String endTimeStr) throws DateTimeParseException {
        this(description, LocalDateTime.parse(endTimeStr, Task.INPUT_DATE_TIME_FORMATTER));
    }

    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
    }

    public boolean isDue(LocalDateTime dateTime) {
        return this.endTime.truncatedTo(ChronoUnit.DAYS).isEqual(dateTime.truncatedTo(ChronoUnit.DAYS));
    }

    @Override
    public String toCommaString() {
        String[] commaStringValues = {
                "D",
                this.isDone ? "X" : " ",
                this.description,
                this.endTime.format(Task.INPUT_DATE_TIME_FORMATTER),
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.endTime.format(Task.DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
