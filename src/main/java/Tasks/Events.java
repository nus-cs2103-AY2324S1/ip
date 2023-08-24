package Tasks;
/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * A child class of Tasks to create tasks that start at a specific date/time and ends at a specific date/time.
 * e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019.
 */
public class Events extends Task {

    protected final String from;
    protected final String to;

    public Events(String description, Boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String getData() {
        String marked = isDone ? "1" : "0";
        return "E | " + marked + " | " + this.taskDesc + " | " + this.from + " | " + this.to;
    }
}
