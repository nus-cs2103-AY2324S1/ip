package ratspeak.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    private final String task;
    private final LocalDate endDate;
    private final boolean isDone;



    public Task(String task, LocalDate endDate) {
        this.task = task;
        this.endDate = endDate;
        this.isDone = false;
    }

    public Task(String task, LocalDate endDate, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        this.endDate = endDate;
    }

    public boolean isUrgent() {
        if (isDone) {
            return false;
        }
        return LocalDate.now().plusWeeks(1).isAfter(endDate)
                && LocalDate.now().isBefore(endDate);
    }

    public abstract Task done();

    public abstract Task undone();

    public abstract String storageText();

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.task.trim();
        }
        return "[ ] " + this.task.trim();
    }
}
