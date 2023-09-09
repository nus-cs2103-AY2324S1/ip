package com.ducky.task;

/**
 * Represents a task with a start time and end time.
 */
public class EventTask extends Task {

    private final String startTime;
    private final String endTime;

    /**
     * Constructs a task with the specified start time and end time.
     * @param desc Description of task.
     * @param startTime Start time of task.
     * @param endTime End time of task.
     */
    public EventTask(String desc, String startTime, String endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    @Override
    public String getSaveFormat() {
        return String.format(
                "E | %s | %s | %s",
                super.getSaveFormat(),
                startTime,
                endTime
        );
    }
}
