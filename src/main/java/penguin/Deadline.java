

package penguin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class Deadline extends Task {
    LocalDate by;
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by); 
    }

    public String getDisplay() {
        return "[D]" + super.getDisplay() + " by " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getSaveDisplay() {
        return "D | " + super.getSaveDisplay() + " | " + this.by.format(ISO_LOCAL_DATE);
    }
}
