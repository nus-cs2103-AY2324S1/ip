package data.task;

import data.exception.DukeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private DateTimeFormatter formatter;

    public EventTask(String description, String startDate, String endDate) throws DukeException {
        super(description);
        try {
            this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            // time should be in format dd/mm/yyyy HHMM(24h)
            LocalDateTime start = LocalDateTime.parse(startDate, formatter);
            LocalDateTime end = LocalDateTime.parse(endDate, formatter);
            if (start.isBefore(end)) {
                this.startDate = start;
                this.endDate = end;
            } else {
                throw new DukeException("Your start date is either the same or after your end date!");
            }
        } catch (DateTimeParseException e) {
            System.out.println("There was an error parsing the date given.");
            e.printStackTrace();
        }
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String getDateTime() {
        return formatter.format(this.startDate) + "," + formatter.format(this.endDate);
    }

    @Override
    public String toString() {
        DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "[E]" + super.toString() + " (from: " + stringFormatter.format(this.startDate) +
                " to: " + stringFormatter.format(this.endDate) + ")";
    }
}
