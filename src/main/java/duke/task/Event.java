package duke.task;

import java.time.LocalDate;

import duke.exceptions.TimeParsingException;
import duke.time.Time;

public class Event extends Task {

    private final LocalDate startTime;
    private final LocalDate endTime;

    public Event(String taskName, String startTime, String endTime) throws TimeParsingException {
        super(taskName);
        this.startTime = Time.parseTime(startTime);
        this.endTime = Time.parseTime(endTime);
    }

    public Event(String taskName, String startTime, String endTime, boolean isDone) throws TimeParsingException {
        super(taskName, isDone);
        this.startTime = Time.parseTime(startTime);
        this.endTime = Time.parseTime(endTime);
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String getTaskTime() {
        return " (from: " + Time.formatTime(startTime) + " to: " + Time.formatTime(endTime) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + this.getTaskName() + " | "
            + Time.formatTimeStoring(startTime) + "--"
            + Time.formatTimeStoring(endTime);
    }
}
