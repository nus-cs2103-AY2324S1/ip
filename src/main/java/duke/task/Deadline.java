package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;
    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by") - 1));
        int byIndex = description.indexOf("/by");
        this.deadline = LocalDate.parse(description.substring(byIndex + 4));
    }
    @Override
    public String getInput() {
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
