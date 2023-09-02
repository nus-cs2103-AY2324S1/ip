package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate dueBy;

    public Deadline(String name, LocalDate dueBy, String isDone) {
        super(name, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toDataString() {
        return super.toDataString() + " | " + dueBy.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        String str = "[D] " + super.getStatus() + " " + super.name + " (by: " +
                dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return str;
    }
//    @Override
//    public String toDataString() {
//        return super.toDataString() + " | " + dueBy;
//    }
//
//    @Override
//    public String toString() {
//        String str = "[D] " + super.getStatus() + " " + super.name + " (by: " + this.dueBy + ")";
//        return str;
//    }
}
