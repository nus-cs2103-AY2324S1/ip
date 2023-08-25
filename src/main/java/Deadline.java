/**
 * Represents a task with a specified deadline.
 */
public class Deadline extends Task {
    private String endTime;

    /**
     * Constructs a deadline task with the given name and end time.
     *
     * @param name    The name of the task.
     * @param endTime The end time of the task.
     */
    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = DateFormatter.format(endTime, "MMM d yyyy");
    }

    /**
     * Generates a formatted string representation of the deadline task.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endTime);
    }
}
