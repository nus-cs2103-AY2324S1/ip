/**
 * Encapsulates a Deadline.
 * Deadlines are tasks that need to be done
 * before a specific date/time.
 *
 * @author Rayson
 */
public class Deadline extends Task{

    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] | %s | %s", super.toString(), this.deadline);
    }

}
