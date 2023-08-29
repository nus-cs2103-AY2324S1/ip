package com.mimi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);

        this.startTime = startTime;
        this.endTime = endTime;

    }
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
    @Override
    public String writeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return String.format("%s (from: %s to: %s)",
                this.taskName(),
                this.startTime.format(formatter),
                this.endTime.format(formatter));
    }

}
