public class Deadline extends Task {

    protected String due;
    public Deadline(String details, String due) {
        super(details);
        this.due = due;
    }

    public Deadline(String details, boolean isCompleted, String due) {
        super(details, isCompleted);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
