public class Deadline extends Task {

    protected String by;
    private String identifier;

    public Deadline(String taskDescription, String by, boolean isDone) {
        super(taskDescription, isDone);
        this.by = by;
        this.identifier = "[D]";
    }

    public String toString() {
        return this.identifier + super.toString() + " (by: " + by + ")";
    }
}
