package duke;

import duke.Parser;
import duke.Task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime time;

    public Deadline(String task, String deadlineDetails) {
        super(task);
        LocalDateTime dueDateTime = Parser.formatDate(deadlineDetails);
        this.time = dueDateTime;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String timeToString = Parser.dateToString(this.time);
        return "[D]" + super.toString() + " (by: " + timeToString + ")";
    }
}

