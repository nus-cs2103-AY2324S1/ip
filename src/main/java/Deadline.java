import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + modifyBy(by) + ")";
    }

    public String modifyBy(String by) {
        LocalDate ld = LocalDate.parse(by);
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String forFile() {
        return "deadline " + this.description + " /by " + modifyBy(by);
    }
}