package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String printDesc() {
        try {
            String byString = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
            return "[D]" + super.printDesc() + " (by: " + byString + ")";
        } catch (IllegalArgumentException e) {
            return "     Please key in the dates in the format of YYYY-MM-ddThh:mm:ss";
        }
    }

    @Override
    public String getDescription() {
        return "D~" + super.getDescription() + "~" + this.by;
    }

}
