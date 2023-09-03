package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(boolean isDone, String taskName, LocalDateTime deadline) {
        super(isDone, taskName);
        this.deadline = deadline;
    }

    public String getDeadlineString(LocalDateTime deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy 'at' HH:mm");
        return deadline.format(formatter);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D]" + "[X] " + this.taskName + "(by: " + getDeadlineString(this.deadline) + ")";
        } else {
            return "[D]" + "[ ] " + this.taskName + "(by: " + getDeadlineString(this.deadline) + ")";
        }
    }

    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "D/@/1/@/" + this.taskName + "/@/" + this.deadline;
        } else {
            return  "D/@/0/@/" + this.taskName + "/@/" + this.deadline;
        }
    }

    @Override
    public String toUpdateString(int i) {
        return "D/@/" + i + "/@/" + this.taskName + "/@/" + this.deadline;
    }
}
