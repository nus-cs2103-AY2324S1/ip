package com.mimi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Representation of the Deadline Task.
 * @author Yuheng
 */
public class Deadline extends Task {
    private LocalDateTime deadlineTime;

    /**
     * Creates an instance of the deadline Task.
     * @param taskName the string representation of the task name.
     * @param deadlineTime the deadline time of the task.
     */
    public Deadline(String taskName, LocalDateTime deadlineTime) {
        super(taskName);


        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the string representation of the task type.
     * @return a string that represents what kind of task this is.
     */
    @Override
    public String eventCode() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s\nby: %s",
                this.eventCode(),
                this.getStatusIcon(),
                this.taskName(),
                this.taskEndTime()
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
                this.deadlineTime.getMonth().toString(),
                this.deadlineTime.format(formatter),
                this.deadlineTime.getDayOfWeek()
                );

    }
    /**
     * A string that is used to store the task content into the hard drive
     * @return string representation of the task for storage.
     */
    @Override
    public String writeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        return String.format("%s (by: %s)", this.taskName(), this.deadlineTime.format(formatter));
    }

    @Override
    public boolean isUrgent() {
        LocalDateTime currentTime = LocalDateTime.now();

        long daysBetween = ChronoUnit.DAYS.between(currentTime, this.deadlineTime);

        return daysBetween < 7 && !isOverdue();
    }

    @Override
    public boolean isOverdue() {
        LocalDateTime currentTime = LocalDateTime.now();

        return this.deadlineTime.isBefore(currentTime);
    }

    @Override
    public boolean equals(Object task) {
        if (!(task instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) task;

        return this.taskName().equals(deadline.taskName())
                && this.deadlineTime.equals(deadline.deadlineTime);
    }
}
