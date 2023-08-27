import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byDate;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + this.description
                + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    @Override
    public String fileString() {
        return "D|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + byDate;
    }
}
