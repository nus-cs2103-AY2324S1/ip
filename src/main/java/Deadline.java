public class Deadline extends Task{

    private String by;

    /**
     * Constructs a Deadline with a given description. Completion of the task
     * is false by default
     *
     * @param description The description of the task.
     * @param by The deadline the task must be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string of the status of the task
     * @return String containing completion status, type, description,
     * and deadline of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")" ;
    }
}
