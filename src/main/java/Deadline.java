import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Deadline extends Task {
    @JsonProperty("deadline")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate DEADLINE;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public LocalDate getDEADLINE() {
        return this.DEADLINE;
    }

    public Deadline() {
        super();
        this.DEADLINE = LocalDate.now();
        this.taskType = TaskType.DEADLINE;
    }

    public Deadline(String deadlineDesc, boolean isDone, LocalDate deadline) {
        super(deadlineDesc, isDone);
        this.DEADLINE = deadline;
        this.taskType = TaskType.DEADLINE;
    }

    public Deadline(String deadlineDesc, boolean isDone, TaskType taskType, LocalDate deadline) {
        super(deadlineDesc, isDone);
        this.DEADLINE = deadline;
        this.taskType = TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "by: " + this.DEADLINE.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
