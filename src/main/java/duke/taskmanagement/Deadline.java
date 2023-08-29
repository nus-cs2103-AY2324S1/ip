package duke.taskmanagement;
import java.time.LocalDate;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }
    @Override
    public String saveToFileString() {
        return "D | " +  convertIsDone() + " | " + description + " | " + this.by+"\n";
    }
}
