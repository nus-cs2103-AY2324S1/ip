import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task implements TimedTask {

    protected String from;
    protected String to;
    protected LocalDate fromDateTime = null;
    protected LocalDate toDateTime = null;

    @Override
    public void processDateTimes() {
        try {
            fromDateTime = TimeProcessor.getLocalDateFromString(this.from);
            toDateTime = TimeProcessor.getLocalDateFromString(this.to);
            this.from = TimeProcessor.getStringFromLocalDate(fromDateTime);
            this.to = TimeProcessor.getStringFromLocalDate(toDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date. Date related operations will not work on this task.");
        }
    }
    @Override
    public boolean fallsOn(LocalDate dateTime) {
        try {
            return (fromDateTime.isEqual(dateTime) || fromDateTime.isBefore(dateTime)) &&
                    (toDateTime.isEqual(dateTime) || toDateTime.isAfter(dateTime));
        } catch (NullPointerException e) {
            return false;
        }
    }
    @Override
    public boolean isAfter(LocalDate dateTime) {
        try {
            return toDateTime.isBefore(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }
    @Override
    public boolean isBefore(LocalDate dateTime) {
        try {
            return fromDateTime.isAfter(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        processDateTimes();
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        processDateTimes();
    }

    @Override
    public String getSaveableString() {
        return String.format("E//%s//%s//%s//%s", getStatusIcon(), description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
