package taskmaster.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String startTime;
    String endTime;
    LocalDate startDate;
    LocalDate endDate;

    public Event(String description, String start, String end, String marked) {
        super(description, marked);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedStartDate = LocalDate.parse(start, formatter);
            LocalDate parsedEndDate = LocalDate.parse(end, formatter);
            this.startDate = parsedStartDate;
            this.endDate = parsedEndDate;
        } catch (java.time.format.DateTimeParseException e) {
            this.startTime = start;
            this.endTime = end;
        }
    }

    public String getStartString() {
        return this.startTime;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + (startTime == null ? this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : startTime)
                + " to "
                + (endTime == null ? this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : endTime)
                + ")";
    }
}
