package duke.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Task class contains an Event with from and to dates */
public class Event extends Task {
    public LocalDate from;
    public LocalDate to;

    /**
     * Initialize Event class.
     *
     * @param task Task.
     * @param from Date when the event starts.
     * @param to Date when the event ends.
     */
    public Event(@JsonProperty("task") String task, @JsonProperty("from") LocalDate from,
                 @JsonProperty("to")LocalDate to) {
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate(from) + " to: " + getDate(to) + ")";
    }
}
