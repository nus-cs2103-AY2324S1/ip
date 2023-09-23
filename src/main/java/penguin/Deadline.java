

package penguin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
/**
 * Deadline is a type of Task with a set to-do-by date.
 */
public class Deadline extends Task {
    LocalDate by;
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }
    /**
     * Returns information about the deadline for output to the user.
     *
     * @return Information about the deadline in user-output format.
     */
    public String getDisplay() {
        return "[D]" + super.getDisplay() + " by " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    /**
     * Returns information about the deadline for internal storage purposes.
     *
     * @return Information about the deadline in internal storage format.
     */
    public String getSaveDisplay() {
        return "D | " + super.getSaveDisplay() + " | " + this.by.format(ISO_LOCAL_DATE);
    }
}
