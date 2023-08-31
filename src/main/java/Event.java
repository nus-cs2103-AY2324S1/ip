import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that inherits from Task.
 * 
 * @var from Representing start time
 * @var to Representing end time
 * 
 * @author Owen Yeo
 */
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for an event object.
     * 
     * @param label Descriptor for the event
     * @param from Start time
     * @param to End time
     */
    Event(String label, String from, String to) {
        super(label);
        this.from = LocalDateTime.parse(from, DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return "E " + super.toSaveString() + " | " + from
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + " - " + to
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + " to: " + to
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + ")";
    }
}
