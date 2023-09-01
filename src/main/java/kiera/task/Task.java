package kiera.task;

import java.time.LocalDate;
import java.time.LocalTime;


public class Task {
    private String description;
    private boolean isDone;
    private LocalDate deadline;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void setStartEnd(String input) {
        String[] inputs = input.split("/");
        String start = inputs[1].replace("from ", "");

        String[] times = start.split("-");
        int y = Integer.parseInt(times[0]);
        int m = Integer.parseInt(times[1]);
        int d = Integer.parseInt(times[2].split(" ")[0]);

        int time = Integer.parseInt(times[2].split(" ")[1]);
        int startHour = (int) Math.floor(time / 100);
        int startMin = time % 100;

        int end = Integer.parseInt(inputs[2].replace("to ", ""));
        int endHour = (int) Math.floor(end / 100);
        int endMin = end % 100;

        this.startDate = LocalDate.of(y, m, d);
        this.startTime = LocalTime.of(startHour, startMin);
        this.endTime = LocalTime.of(endHour, endMin);
    }
    public void setDeadline(String input) {
        this.deadline = LocalDate.parse(input);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }
    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
    public String getDateString() {
        return "";
    }
    public String toStorageString() {
        return "  // " + this.getStatusIcon() + " // " + this.description;
    }

    @Override
    public String toString() {
        return "[ ]" + "[" + this.getStatusIcon() + "] " + this.description;
    }


}
