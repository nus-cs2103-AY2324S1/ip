package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime date;
    //included serialVersionUID indicated as the Task implements Serializable Interface
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String taskString() {
        return "[D]" + super.taskString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
    @Override
    public String saveTaskString() {
        String status = (this.isDone ? "1" : "0");
        return "D" + "|" + status + "|" + this.description
                + "|" + this.date;
    }
}