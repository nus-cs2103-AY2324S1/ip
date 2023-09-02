package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + localDatetoString(by) + ")";
    }
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "| " + localDatetoString(by);
    }

    public String localDatetoString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedDateTime = dateTime.format(outputFormatter);
        return formattedDateTime;
    }
}
