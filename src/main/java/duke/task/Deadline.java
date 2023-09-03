package duke.task;

import java.time.LocalDate;
import duke.exceptions.TimeParsingException;
import duke.time.Time;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String taskName, String deadline) throws TimeParsingException {
        super(taskName);
        this.deadline = Time.parseTime(deadline);
    }

    public Deadline(String taskName, String deadline, boolean isDone) throws TimeParsingException {
        super(taskName, isDone);
        this.deadline = Time.parseTime(deadline);
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String getTaskTime() {
        return " (by: " + Time.formatTime(deadline) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (super.isDone() ? "1" : "0") + " | "
            + this.getTaskName() + " | "
            + Time.formatTimeStoring(deadline);
    }
}
