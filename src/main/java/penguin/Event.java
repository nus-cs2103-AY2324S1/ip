
package penguin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class Event extends Task {
    LocalDate from;
    LocalDate to;
    public Event(String name, String from, String to) {
        super(name);

        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);

    }

    public String getDisplay() {
        return "[E]" + super.getDisplay() + " from " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getSaveDisplay() {
        return "E | " + super.getSaveDisplay() + " | " + this.from.format(ISO_LOCAL_DATE) + " | " + this.to.format(ISO_LOCAL_DATE);
    }
}
