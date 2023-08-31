import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate byDate;
    public Deadline(String item, LocalDate byDate) {
        super(item);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.toString() + ")";
    }
}