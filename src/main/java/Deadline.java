import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    @Override
    public String encode() {
        DateTimeFormatter stringformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "D"+getStatusNumber()+super.description + " /by " + this.by.format(stringformatter);
    }
}