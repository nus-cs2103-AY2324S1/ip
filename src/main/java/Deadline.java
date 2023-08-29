public class Deadline extends Task {
    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description, "D");
        this.deadlineDate = deadlineDate;
    }

    public String toString() {
        return super.toString() + " (by: " + deadlineDate + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + deadlineDate;
    }
}
