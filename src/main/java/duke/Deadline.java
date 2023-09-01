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
        String byString =  this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
        return "[D]" + super.printDesc() + " (by: " + byString + ")";
    }

    @Override
    public String getDescription() {
        return "D~" + super.getDescription() + "~" + this.by;
    }

}
