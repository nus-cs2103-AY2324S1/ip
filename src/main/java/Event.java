import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String type = "Event";
    protected LocalDate from = null;
    protected LocalDate to = null;

    public Event(String description, String from, String to) throws LinusException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);

            if (this.from.isAfter(this.to)) {
                throw new LinusException("☹ OOPS!!! Please specify the start date before/on the same day as the end date.");
            }
        } catch (DateTimeParseException e) {
            throw new LinusException("☹ OOPS!!! Please specify the start and/or end dates in the correct format: yyyy-mm-dd");
        }
    }

    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
