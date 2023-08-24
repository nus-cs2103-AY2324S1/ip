package Tasks;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * A child class of Tasks to create tasks that need to be done before a specific date/time.
 * e.g., submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {

    protected final String by;

    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getData() {
        String marked = isDone ? "1" : "0";
        return "D | " + marked + " | " + this.taskDesc + " | " + this.by;
    }
}
