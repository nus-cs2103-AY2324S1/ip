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
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
