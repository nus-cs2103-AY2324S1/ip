package tasks;

import helpers.DateTimeDetection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDateTime end;

    public Deadline(String description, String end) {
        super(description);
        setDateTime(end);
    }

    public Deadline(String description, boolean isDone, String end) {
        super(description, isDone);
        setDateTime(end);
    }

    public void setDateTime(String input) {
        String[] parts = input.split(" ", 2);
        this.end = DateTimeDetection.detectDateTime(parts[1]);
    }

    @Override
    public String toText() {
        return "D " + this.getDoneStatus() + " " +
                this.description + " /" +
                this.end.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                " (" + DateTimeDetection.formatDateTime(this.end) + ")";
    }
}
