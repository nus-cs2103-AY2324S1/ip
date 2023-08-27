import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A concrete implementation of Event Task </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * A constructor that constructs an Event Task
     * @param taskName The task name for the constructed
     * @param start The start of the period for the constructed event
     * @param end The end of the period for the constructed event
     */
    @JsonCreator
    public Event(@JsonProperty("taskName") String taskName
            , @JsonProperty("start") LocalDate start, @JsonProperty("end") LocalDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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
