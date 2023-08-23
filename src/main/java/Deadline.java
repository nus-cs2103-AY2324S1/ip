public class Deadline extends Task {
    private String dueDate;

    public Deadline(String dueDate, String description) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dueDate + ")";
    }
}
