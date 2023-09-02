package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that start at a specific date/time and ends at a specific date/time.
 *
 * @author Sebastian Tay
 */
public class Event extends Task {
    //TODO
    static final String SYMBOL = "E";

    protected String from;
    protected LocalDateTime start;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        this.start = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

    }

    public Event(String description, String period, boolean isDone) {
        super(description, isDone);
        this.from = period.split("-")[0];
        this.to = period.split("-")[1];

        this.start = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + super.getDescription() + "(from: " + this.start.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HHmm")) + "H to: " + this.to + "H)";
    }

    @Override
    public String convertToStorageForm() {
        final String SEPARATOR = "::";
        final String status = isDone() ? "1" : "0";
        final String period = this.from + "-" + this.to;

        //E::0::project meeting::Aug 6th 2pm-4pm
        return SYMBOL + SEPARATOR + status + SEPARATOR + getDescription() + SEPARATOR + period;
    }
}
