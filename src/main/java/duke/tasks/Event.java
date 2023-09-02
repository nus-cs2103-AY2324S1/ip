package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public LocalDateTime fromDateTime;
    public LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .toFormatter();

        try {
            this.fromDateTime = LocalDateTime.parse(from, formatter);
            this.toDateTime = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            this.fromDateTime = null;
            this.toDateTime = null;
            System.out.println("Please use the following formats:\n"
                    + "event task /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n"
                    + "deadline task /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm");
        }
    }

    @Override
    public String type() {
        return "[E]";
    }

    @Override
    public String toString() {
        return type() + super.toString() + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + " to " + toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}