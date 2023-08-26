import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String deadline;

    /**
     * A constructor that constructs a Deadline Task
     * @param taskName The task name of the constructed Deadline Task
     * @param deadline The deadline of the constructed Deadline Task
     */
    @JsonCreator
    public Deadline(@JsonProperty("taskName") String taskName, @JsonProperty("deadline") String deadline) {
        super(taskName);
        this.deadline = deadline;
    }


    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
