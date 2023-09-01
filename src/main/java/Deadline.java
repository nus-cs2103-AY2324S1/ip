import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;
    protected String by;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
        this.by = byDate.toString() + " " + byTime.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
