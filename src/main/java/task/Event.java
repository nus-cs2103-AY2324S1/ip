package task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an Event Task using the task name, start date and end date.
     * @param taskName The task name for the constructed.
     * @param start The start of the period for the constructed event.
     * @param end The end of the period for the constructed event.
     */
    @JsonCreator
    public Event(@JsonProperty("taskName") String taskName
            , @JsonProperty("start") LocalDate start, @JsonProperty("end") LocalDate end) {
        super(taskName);
        this.start = start;
        this.end = end;

        assert this.start != null : "start date of an Event should not be null";
        assert this.end != null : "end date of an Event should not be null";
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setStart(LocalDate start) {
        this.start = start;
        assert this.start != null : "start date of an Event should not be null";
    }

    public void setEnd(LocalDate end) {
        this.end = end;
        assert this.end != null : "end date of an Event should not be null";
    }

    private String getFormattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + getFormattedDate(this.start)
                + " to: " + getFormattedDate(this.end) + ")";
    }
}
