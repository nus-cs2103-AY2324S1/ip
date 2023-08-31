import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Solution below adapted from: https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html#:~:text=Extension%3A%20A%2D-,Classes,-A%2DClasses
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Make deadline task
     * @param description task description
     * @param by task deadline
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
