package data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DeadlineTask extends Task {
    private LocalDateTime deadlineDate;
    private DateTimeFormatter formatter;
    public DeadlineTask(String description, String deadlineDate) {
        super(description);
        try {
            // time should be in format dd/mm/yyyy HHMM(24h)
            this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.deadlineDate = LocalDateTime.parse(deadlineDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("There was an error parsing the date given.");
            e.printStackTrace();
        }
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String getDateTime() {
        return formatter.format(this.deadlineDate);
    }

    @Override
    public String toString() {
        DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "[D]" + super.toString() + " (by: " + stringFormatter.format(this.deadlineDate) + ")";
    }
}