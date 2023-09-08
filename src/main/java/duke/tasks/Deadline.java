package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String PRINT_FORMAT = "[D]%s %s (%s)";
    private static final String STORE_FORMAT = "[D] | %s | %s | %s";
    private final LocalDateTime end;

    public Deadline(String info, LocalDateTime by) {
        super(info, TaskType.DEADLINE);
        this.end = by;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String saveString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim(), end.format(dtFormat));
    }

    @Override
    public String toString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription(), end.format(dtFormat));
    }
}

