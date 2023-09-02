package duke.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonTypeName("Model.Event")
public class Event extends Task {
    public LocalDate from;
    public LocalDate to;
    public Event(@JsonProperty("task") String task, @JsonProperty("from") LocalDate from, @JsonProperty("to")LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    private String getDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate(from) + " to: " + getDate(to) + ")";
    }
}
