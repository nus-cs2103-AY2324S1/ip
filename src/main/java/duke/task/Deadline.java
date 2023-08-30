package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String type = "[D]";
    private LocalDateTime deadline;
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.done ? 1 : 0
                , this.name, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return type + super.toString() + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
