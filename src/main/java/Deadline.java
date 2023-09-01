import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Deadline")
public class Deadline extends Task {
    public String dateTime;
    public Deadline(@JsonProperty("task") String task, @JsonProperty("dateTime") String dateTime) {
        super(task);
        this.dateTime=dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}
