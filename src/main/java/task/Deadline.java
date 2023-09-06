package task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructs a Deadline Task using the task name and deadline.
     * @param taskName The task name of the constructed Deadline Task.
     * @param deadline The deadline of the constructed Deadline Task.
     */
    @JsonCreator
    public Deadline(@JsonProperty("taskName") String taskName, @JsonProperty("deadline") LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;

        assert this.deadline != null : "deadline should not be null";
    }


    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;

        assert this.deadline != null : "deadline should not be null";
    }

    private String getFormattedDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
