import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String output = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + output + ")";
    }

    @Override
    public String write() {
        String output = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "D | " + super.write() + " | " + by.toString();
    }
}