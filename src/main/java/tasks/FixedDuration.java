package tasks;

public class FixedDuration extends Task {

    /**
     * The fixed duration of the task.
     */
    protected String duration;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param duration The fixed duration of the task.
     */
    public FixedDuration(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (for: " + this.duration + ")";
    }
}
