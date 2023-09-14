package ren.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an event
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends Task {

    private String startTime;
    private String endTime;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Event() {
        super();
        this.startTime = "";
        this.endTime = "";
        this.taskType = ren.task.Task.TaskType.EVENT;
    }

    public Event(String eventDesc, boolean isDone, String startTime, String endTime) {
        super(eventDesc, isDone);
        this.taskType = ren.task.Task.TaskType.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String eventDesc, boolean isDone, ren.task.Task.TaskType taskType, String startTime, String endTime) {
        super(eventDesc, isDone);
        this.taskType = ren.task.Task.TaskType.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "from: " + this.startTime
                + "to: " + this.endTime;
    }
}
