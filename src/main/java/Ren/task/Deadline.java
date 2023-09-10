package ren.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;


/**
 * Represents a deadline
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deadline extends Task {
    @JsonProperty("deadline")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private java.time.LocalDate deadline;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public java.time.LocalDate getDeadline() {
        return this.deadline;
    }


    public Deadline(String deadlineDesc, boolean isDone, ren.task.Task.TaskType taskType, java.time.LocalDate deadline) {
        super(deadlineDesc, isDone);
        this.deadline = deadline;
        this.taskType = ren.task.Task.TaskType.DEADLINE;
    }

    public Deadline(String deadlineDesc, boolean isDone, java.time.LocalDate deadline) {
        super(deadlineDesc, isDone);
        this.deadline = deadline;
        this.taskType = ren.task.Task.TaskType.DEADLINE;
    }

    public Deadline() {
        super();
        this.deadline = java.time.LocalDate.now();
        this.taskType = ren.task.Task.TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "by: " + this.deadline.format(java.time.format.DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
