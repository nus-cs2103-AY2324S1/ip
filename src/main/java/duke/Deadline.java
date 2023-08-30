package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Deadline extends Task{
    LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(dateTimeOutputFormatter) + ")";
    }

    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ Command.DEADLINE.getCommand(), this.getDone() ? "1" : "0", this.getTaskName(),
                this.by.format(Duke.dateTimeInputFormatter) };
        return String.join(" / ", state);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.by.toLocalDate());
    }
}
