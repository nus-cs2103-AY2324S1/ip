package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Task class contains an Event with from and to dates */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Initialize Event class.
     *
     * @param task Task.
     * @param from Date when the event starts.
     * @param to Date when the event ends.
     */
    public Event(@JsonProperty("task") String task, @JsonProperty("from") LocalDate from,
                 @JsonProperty("to") LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts LocalDate to String.
     *
     * @param localDate LocalDate to convert to.
     * @return String in the form of "MMM dd yyyy"
     */
    private String getDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate(from) + " to: " + getDate(to) + ")";
    }
}
