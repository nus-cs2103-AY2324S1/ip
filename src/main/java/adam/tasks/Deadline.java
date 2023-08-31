package adam.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements Serializable {
    protected LocalDate by;
    public Deadline(String text, String by) {
        super(text);
        this.by = LocalDate.parse(by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
