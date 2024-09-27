package duke.task;

/**
 * FixedDurationTask class represents a task that takes a fixed amount of time
 * but does not have a fixed start/end time.
 */
public class FixedDurationTask extends Task {
    protected int duration;
    protected String units;

    /**
     * Constructs a FixedDurationTask object.
     *
     * @param description Description of the task.
     * @param duration Duration needed for the task.
     * @param units Units used for the task.
     */
    public FixedDurationTask(String description, int duration, String units) {
        super(description);
        this.duration = duration;
        this.units = units;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + duration + " " + units + ")";
    }

    @Override
    public String toFileFormat() {
        return "F" + super.toFileFormat() + " | needs " + duration + " " + units + "\n";
    }
}
