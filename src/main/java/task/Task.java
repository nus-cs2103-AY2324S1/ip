package task;

import java.io.IOException;
import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task fromString(String line) throws IOException {
        if (line.startsWith("T")) {
            String[] parts = line.split("\\|");
            boolean isCompleted = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task = new ToDo(description);
            task.toggleIsDone(isCompleted);

            return task;
        } else if (line.startsWith("D")) {
            String[] parts = line.split("\\|");
            boolean isCompleted = parts[1].trim().equals("1");
            String description = parts[2].trim();
            String by = parts[3].trim();
            LocalDate date = LocalDate.parse(by);

            Task task = new Deadline(description, date);
            task.toggleIsDone(isCompleted);

            return task;
        } else if (line.startsWith("E")) {
            String[] parts = line.split("\\|");
            boolean isCompleted = parts[1].trim().equals("1");
            String description = parts[2].trim();
            String[] time = parts[3].trim().split("-");

            Task task = new Event(description, time[0].trim(), time[1].trim());
            task.toggleIsDone(isCompleted);

            return task;
        } else {
            throw new IOException("Corrupted File. What you doin' bruh...");
        }
    }

    public void toggleIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        return description;
    }
}