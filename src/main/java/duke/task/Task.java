package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " +  this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String localDateTimeToString(LocalDateTime data) {
        String day = String.valueOf(data.getDayOfMonth());
        String month = data.getMonth().toString();
        month = month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase();
        String year = String.valueOf(data.getYear());
        String hour = data.format(DateTimeFormatter.ofPattern("h"));
        String minute = data.format(DateTimeFormatter.ofPattern("mm"));
        String amPm = data.format(DateTimeFormatter.ofPattern("a"));

        return String.format("%s of %s %s, %s:%s%s", day, month, year, hour, minute, amPm);
    }
}
