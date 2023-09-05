package duke;

import java.time.*;
import java.time.format.*;
public class Deadline extends Task {
    protected LocalDate by;


    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public String changeFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateFormatted = date.format(formatter);
        return date.format(formatter);
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + changeFormat(by )+ ")";
    }
}
