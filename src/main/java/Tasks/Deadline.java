package Tasks;

public class Deadline extends Task {
    /**
     * Taken from the Partial Solution given on https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
     * A child class of Tasks to create tasks with a deadline.
     */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String addDataFormat() {
        return "D " + super.addDataFormat() + " | " + by;
    }
}