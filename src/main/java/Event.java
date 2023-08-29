import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        parseDateTime(from, to);
        this.from = from;
        this.to = to;
    }

    public boolean isValidDate(String dateTimeString) {
        try {
            String[] parts = dateTimeString.split(" ");
            LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void parseDateTime(String fromdateTime, String todateTime) {
        if (isValidDate(fromdateTime) && isValidDate(todateTime)) {
            String[] fromparts = fromdateTime.split(" ");
            fromDate = LocalDate.parse(fromparts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            fromTime = LocalTime.parse(fromparts[1], DateTimeFormatter.ofPattern("HHmm"));

            String[] toparts = todateTime.split(" ");
            toDate = LocalDate.parse(toparts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            toTime = LocalTime.parse(toparts[1], DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    @Override
    public String toString() {
        if (fromDate != null && toDate != null) {
            return "[E]" + super.toString() + " (from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + fromTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + toTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + from + " to: "
                    + to + ")";
        }

    }

    @Override
    public String toSaveString() {
        if (fromDate != null && toDate != null) {
            return "E," + isDone + "," + description + "," + fromDate + " " + fromTime + "," + toDate + " " + toTime;
        } else {
            return "E," + isDone + "," + description + "," + from + "," + to;
        }
    }
}