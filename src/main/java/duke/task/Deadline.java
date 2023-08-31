package duke.task;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime DateAndTime;

    public Deadline(String description, LocalDateTime DateAndTime) {
        super(description, Type.DEADLINE);
        this.DateAndTime = DateAndTime;
    }

    @Override
    public String toString() {
        // eg 2nd Dec 2019 6pm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + DateAndTime.format(formatter) + ")";
    }
}
