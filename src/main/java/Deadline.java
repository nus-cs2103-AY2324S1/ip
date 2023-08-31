import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime endTime;

    public Deadline(String description, String endTimeStr) throws DateTimeParseException {
        this(description, LocalDateTime.parse(endTimeStr, Task.INPUT_DATE_TIME_FORMATTER));
    }

    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
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
