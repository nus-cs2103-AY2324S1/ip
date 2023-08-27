import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A concrete implementation of Deadline Task </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class Deadline extends Task {
    // private String deadline;
    private LocalDate deadline;

    /**
     * A constructor that constructs a Deadline Task
     * @param taskName The task name of the constructed Deadline Task
     * @param deadline The deadline of the constructed Deadline Task
     */
    @JsonCreator
    public Deadline(@JsonProperty("taskName") String taskName, @JsonProperty("deadline") LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }


    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    private String getFormattedDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
