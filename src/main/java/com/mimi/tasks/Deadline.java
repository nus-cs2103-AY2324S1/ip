package com.mimi.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadlineTime;


    public Deadline(String taskName, LocalDateTime deadlineTime) {
        super(taskName);


        this.deadlineTime = deadlineTime;
    }

    @Override
    public String eventCode() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.eventCode(),
                this.getStatusIcon(),
                this.taskName(),
                this.taskEndTime()
        );
    }

    @Override
    public String taskEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy HH:mm");
        //format : MMM dd yyyy HH:mm ({dayOfWeek})
        return String.format(
                "%s %s (%s)",
                this.deadlineTime.getMonth().toString(),
                this.deadlineTime.format(formatter),
                this.deadlineTime.getDayOfWeek()
                );

    }

    @Override
    public String writeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return String.format("%s (by: %s)", this.taskName(), this.deadlineTime.format(formatter));
    }
}
