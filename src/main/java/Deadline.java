import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate byDate;
    public Deadline(String item, LocalDate byDate) {
        super(item);
        this.byDate = byDate;
    }
    public Deadline(String item, LocalDate byDate, boolean done) {
        super(item, done);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.toString() + ")";
    }
    @Override
    public String saveItem() {
        return "D | " + super.saveItem() + "by: " + byDate.toString() + "\n";
    }
}