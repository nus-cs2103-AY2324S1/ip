import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String start;
    private String end;

    /**
     * A constructor that constructs an Event Task
     * @param taskName The task name for the constructed
     * @param start The start of the period for the constructed event
     * @param end The end of the period for the constructed event
     */
    @JsonCreator
    public Event(@JsonProperty("taskName") String taskName
            , @JsonProperty("start") String start, @JsonProperty("end") String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
