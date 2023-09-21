package duke;

/**
 * Encapsulates FixedDurationTask which are Tasks with a fixed duration.
 */
public class FixedDurationTask extends Task {
    private String duration;

    /**
     * Constructs a FixedDurationTask with specified description and duration.
     */
    public FixedDurationTask(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Constructs a FixedDurationTask with specified description, duration, and
     * whether it is done.
     */
    public FixedDurationTask(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    /**
     * Returns a String representation for FixedDurationTask for output.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (for: "
                + this.duration + ")";
    }

    /**
     * Returns a String representation of FixedDurationTask for storage.
     */
    @Override
    public String toTxt() {
        return "F | " + super.toTxt()
                + " | " + this.duration;
    }

}
