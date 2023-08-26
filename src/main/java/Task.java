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
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveString() {
        return String.format("%s|%s", isDone ? "1" : "0", description);
    }

    public static Task parseTask(String line) {
        String[] split = line.split("\\|");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;

        switch (type) {
            case "T": {
                task = new Todo(description);
                if (isDone) task.markAsDone();
                break;
            }
            case "D": {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime by = LocalDateTime.parse(split[3], formatter);
                task = new Deadline(description, by);
                if (isDone) task.markAsDone();
                break;
            }
            case "E": {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(split[3], formatter);
                LocalDateTime end = LocalDateTime.parse(split[4], formatter);
                task = new Event(description, start, end);
                if (isDone) task.markAsDone();
                break;
            }
        }
        return task;
    }
}
