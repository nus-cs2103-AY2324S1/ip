import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.getBy() + ")";
    }
}
