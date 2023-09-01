import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .toFormatter();

        try {
            this.dateTime = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            this.dateTime = null;
            System.out.println("Please use the following formats:\n"
                    + "deadline task /by yyyy-mm-dd hhmm\n"
                    + "deadline task /by dd/mm/yyyy hhmm\n"
                    + Duke.horizontalLine);
        }
    }

    @Override
    public String type() {
        return "[D]";
    }

    @Override
    public String toString() {
        return type() + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}