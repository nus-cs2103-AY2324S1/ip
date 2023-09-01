package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    public Deadline(String name, LocalDateTime date, String input) {
        super(name, input);
        this.date = date;
    }

    @Override
    public String toString() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        if (!this.getDone()) {
            return "[D][ ] " + this.getName() + "(by: " + this.date.format(formatter) + ")";
        } else {
            return "[D][X] " + this.getName() + "(by: " + this.date.format(formatter) + ")";
        }
    }
}

