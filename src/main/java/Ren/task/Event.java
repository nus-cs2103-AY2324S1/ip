package ren.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends Task {

    private final String START_TIME;
    private final String END_TIME;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getSTART_TIME() {
        return this.START_TIME;
    }

    public String getEND_TIME() {
        return this.END_TIME;
    }

    public Event() {
        super();
        this.START_TIME = "";
        this.END_TIME = "";
        this.taskType = TaskType.EVENT;
    }

    public Event(String eventDesc, boolean isDone, String startTime, String endTime) {
        super(eventDesc, isDone);
        this.taskType = TaskType.EVENT;
        this.START_TIME = startTime;
        this.END_TIME = endTime;
    }

    public Event(String eventDesc, boolean isDone, TaskType taskType, String startTime, String endTime) {
        super(eventDesc, isDone);
        this.taskType = TaskType.EVENT;
        this.START_TIME = startTime;
        this.END_TIME = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "from: " + this.START_TIME +
                "to: " + this.END_TIME;
    }
}
