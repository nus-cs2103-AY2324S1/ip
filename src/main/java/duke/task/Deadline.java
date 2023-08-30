package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime date;

    protected String by;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
    public String getBy() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }

}
