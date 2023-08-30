package jeeves.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String desc, String by, boolean status) {
        super(desc, status);
        this.by = by;
    }

    /**
     * Getter method for the Task deadline
     *
     * @return Deadline of the task
     */
    public String getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        return String.format(this.id + ". [D]" + super.toString() + " (by: " + by + ")");
    }
}
