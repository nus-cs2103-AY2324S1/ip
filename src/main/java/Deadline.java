/**
 * Represents a Deadline event in the Richie application
 */
class Deadline extends Task {
    private String by;

    /**
     * Constructor for the Deadline task
     * @param description Description of the task
     * @param by The time that the task has to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string date and time of when the task should be done by
     * @return String of the date and time of deadline of task
     */
    public String getBy() {
        return this.by;
    }
}
