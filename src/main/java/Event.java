import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Event")
public class Event extends Task{
    public String from;
    public String to;
    public Event(@JsonProperty("task") String task, @JsonProperty("from") String from, @JsonProperty("to")String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
