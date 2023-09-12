package duke.task;

public class Recurring extends Task {
    private String recurring;

    /**
     * Constructor method for an Event.
     * @param taskDescription Description of the task
     * @param recurring Recurrence of the Recurring task
     */
    public Recurring(String taskDescription, String recurring) {
        super(taskDescription);
        this.recurring = recurring;
    }

    /**
     * Retrieve the recurrence of the Recurring task.
     * @return A String representation of the recurrence of the Recur.
     */
    public String getRecurrence() {
        return this.recurring;
    }

    @Override
    public String toString() {
        assert (recurring != null) : "The recurrence of a Recurring task cannot be null.";

        return "[R]" + super.toString() + " (every: " + this.recurring + ")";
    }
}
