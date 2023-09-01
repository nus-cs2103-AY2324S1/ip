import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonTypeName("Deadline")
public class Deadline extends Task {
    public LocalDate dateTime;
    public Deadline(@JsonProperty("task") String task, @JsonProperty("dateTime") LocalDate dateTime) {
        super(task);
        this.dateTime=dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
