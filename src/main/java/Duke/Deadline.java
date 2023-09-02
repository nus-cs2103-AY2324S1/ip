package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate by;
    public Deadline (String val, String by) {
        super(val);
        this.by = LocalDate.parse(by,DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
