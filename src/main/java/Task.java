import java.time.LocalDateTime;
import java.time.LocalTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " + description
                       : "[ ] " + description );
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    public static Task parseFromString(String input) {
        String[] parts = input.split(" \\| ");
        String type = parts[0];
        int isDone = Integer.parseInt(parts[1]);
        String description = parts[2];

        if (type.equals("T")) {
            Task task = new Todo(description);
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else if (type.equals("D")) {
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            Task task = new Deadline(description, by);
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else if (type.equals("E")) {
            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalTime to = LocalTime.parse(parts[4]);
            Task task = new Event(description, from, to);
            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } else {
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }
    @Override
    public String toString() {
        return String.format("Task | %d | %s", isDone ? 1 : 0, description);
    }

}
