package duke.freetimefinder;

import duke.Ui;

import java.time.LocalDateTime;

/**
 * An interval with start time and end time.
 */
public class TimeInterval {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a now TimeInterval given start and end time.
     *
     * @param from The start time of the event as a LocalDateTime object.
     * @param to   The end time of the event as a LocalDateTime object.
     */
    public TimeInterval(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        String output = String.format("From: %s", Ui.outputDateTime(from));
        if (this.to == null) {
            return output;
        }
        return output + String.format(" To: %s", Ui.outputDateTime(to));
    }
}
