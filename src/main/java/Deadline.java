public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to create a Deadline object.
     *
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to get the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns deadline task information in format for saving.
     * Format is D | [1 if completed, 0 if not completed] | [task description] | [by]
     *
     * @return Deadline task information in format for saving
     */
    @Override
    public String getInformationForSaving() {
        return "D | " + super.getInformationForSaving() + " | " + this.by;
    }
}
