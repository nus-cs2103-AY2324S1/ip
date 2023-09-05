package chad.task;

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
        return (isDone ? "X" : ""); // mark done task with X
    }

    public String isDoneString() {
        return isDone ? "1" : "0";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public abstract String toString();

    public abstract String toFileFormat(DateTimeFormatter formatter);

    public static Task fromFileFormat(String line, DateTimeFormatter formatter) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                LocalDateTime dueDate = LocalDateTime.parse(parts[3], formatter);
                task = new Deadline(description, dueDate);
                break;
            case "E":
                LocalDateTime start = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime end = LocalDateTime.parse(parts[4], formatter);
                task = new Event(description, start, end);
                break;
        }

        if (task != null && isDone) {
            task.mark();
        }

        return task;
    }
    String getDescription() {
        return description;
    }

    boolean isDone() {
        return isDone;
    }

}
