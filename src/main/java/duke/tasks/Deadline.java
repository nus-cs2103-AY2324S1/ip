package duke.tasks;

/**
 * Special kind of task that has a description and a deadline
 */
public class Deadline extends Task {
    protected final String type = "D";
    protected String date;

    /**
     * Constructor for the Deadline class.
     * @param description Describes the task
     * @param date When the task should be completed by
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }
    @Override
    public void mark() {
        this.isDone = true;
    }
    @Override
    public void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return this.type + " | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.date;
    }
}