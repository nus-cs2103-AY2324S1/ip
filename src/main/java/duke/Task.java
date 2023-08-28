package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public void mark() {
        this.setDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this);
    }

    public void unmark() {
        this.setUndone();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this);
    }

    public String toStringForSave() {
        int status = this.isDone ? 1 : 0;
        return status + " | " + this.description;
    }

    public LocalDateTime parseStringToTime(String timeString) {
        return LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    public String convertTimeToString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm"));
    }

    public String convertTimeForSave(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}