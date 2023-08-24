public class Deadline extends Task {
    private String by;

    public Deadline(String task, String deadline) {
        super(task);
        by = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
