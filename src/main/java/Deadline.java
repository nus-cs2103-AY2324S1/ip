import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDate by;

    Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = LocalDate.parse(by);
    }

    String getTaskType() {
        return "Deadline";
    }

    String getByFormatted() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    String getBy() {
        return this.by.toString();
    }

    @Override
    public String toString() {
        return "[D][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name + " (by: " + this.getByFormatted() + ")";
    }

    String formatTaskForSaving() {
        return "[D][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name + " (by: " + this.getBy() + ")";
    }


}
