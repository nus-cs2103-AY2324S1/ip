package com.mimi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of the Event task.
 * @author Yuheng
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an instance of the event task.
     * @param taskName the string representation of the event name.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     */
    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);

        this.startTime = startTime;
        this.endTime = endTime;

    }
    /**
     * Returns the string representation of the task type.
     * @return a string that represents what kind of task this is.
     */
    @Override
    public String eventCode() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.eventCode(),
                this.getStatusIcon(),
                this.taskName(),
                this.taskStartTime(),
                this.taskEndTime()
        );
    }


    /**
     * A string representation of the start time of the task, if any.
     * @return a formatted string that shows the start time of the task.
     */
    @Override
    public String taskStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy HH:mm");
        //format : MMM dd yyyy HH:mm ({dayOfWeek})
        return String.format(
                "%s %s (%s)",
                this.startTime.getMonth().toString(),
                this.startTime.format(formatter),
                this.startTime.getDayOfWeek()
        );
    }

    /**
     * A string representation of the end time of the task, if any.
     * @return a formatted string that shows the end time of the task.
     */
    @Override
    public String taskEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy HH:mm");
        //format : MMM dd yyyy HH:mm ({dayOfWeek})
        return String.format(
                "%s %s (%s)",
                this.endTime.getMonth().toString(),
                this.endTime.format(formatter),
                this.endTime.getDayOfWeek()
        );

    }

    /**
     * A string that is used to store the task content into the hard drive
     * @return string representation of the task for storage.
     */
    @Override
    public String writeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return String.format("%s (from: %s to: %s)",
                this.taskName(),
                this.startTime.format(formatter),
                this.endTime.format(formatter));
    }

}
