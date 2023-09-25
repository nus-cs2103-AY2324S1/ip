package duke.tasks;

/**
 * An extension of <code>Task</code>, a <code>FixedDurationTask</code> object keeps track of
 * a task that takes a duration of time to complete at no particular schedule.
 */

public class FixedDurationTask extends Task {
    protected String duration;

    /**
     * The class constructor.
     *
     * @param description Description of the <code>FixedDurationTask</code> object.
     * @param duration Duration of the task.
     */
    public FixedDurationTask(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Gets the value of duration.
     *
     * @return duration Duration of the task.
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * Returns the string form of the <code>FixedDurationTask</code> object, for writing to file.
     */
    @Override
    public String storedString() {
        return "F | " + super.storedString() + " | " + getDuration();
    }

    /**
     * Returns the string form of the <code>FixedDurationTask</code> object, for display to user.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (for: " + getDuration() + ")";
    }
}
