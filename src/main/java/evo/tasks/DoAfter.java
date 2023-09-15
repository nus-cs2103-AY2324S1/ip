package evo.tasks;

/**
 * Represents a task that needs to be done after a specified period of time.
 * This class extends the base Task class and adds a period attribute to
 * specify what or when the task should be done after.
 */
public class DoAfter extends Task {

    /**
     * The specific task/period after which the task should be done.
     */
    protected String period;

    /**
     * Constructs a new DoAfterTask object with the given description and period.
     *
     * @param description The description of the task.
     * @param period The specific task/time after which the task should be done.
     */
    public DoAfter(String description, String period) {
        super(description);
        this.period = period;
    }

    /**
     * Generates a formatted output message for the DoAfterTask task.
     *
     * @return The output string representation of the DoAfterTask task.
     */
    @Override
    public String outputMsg() {
        int result = (isDone) ? 1 : 0;
        return "DA | " + result + " | " + description + " | " + this.period;
    }

    /**
     * Returns a string representation of the DoAfter task.
     *
     * @return The string representation of the DoAfter task with the specific task/time after which the task should
     *     be done.
     */
    @Override
    public String toString() {
        return "[DA]" + super.toString() + " (after " + this.period + ")";
    }
}
