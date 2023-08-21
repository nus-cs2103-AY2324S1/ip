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

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

}
