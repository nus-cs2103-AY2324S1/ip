import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
