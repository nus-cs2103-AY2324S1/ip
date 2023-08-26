import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

     public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.byDate = by;
    }

    public boolean isDueOn(LocalDate date) {
        return this.byDate.equals(date);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}