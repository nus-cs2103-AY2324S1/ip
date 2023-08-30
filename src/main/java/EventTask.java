import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventTask(String description, String startDate, String endDate) {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            // time should be in format dd/mm/yyyy HHMM(24h)
            this.startDate = LocalDateTime.parse(startDate, formatter);
            this.endDate = LocalDateTime.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("There was an error parsing the date given.");
            e.printStackTrace();
        }

    }

    public String getType() {
        return "Event";
    }

    public String getDateTime() {
        return this.startDate + "," + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
