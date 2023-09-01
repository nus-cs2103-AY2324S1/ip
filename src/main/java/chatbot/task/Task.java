package chatbot.task;

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

    /**
     * Get description of the task.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unMark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() {
        String done = isDone ? "1" :"0";
        return String.format("T | %s | %s", done, description);
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split("\\|");

        // Error handling for incorrect line format
        if (parts.length < 2) {
            // Handle this case appropriately
            return null;
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (type) {
            case "T":
                task = new Todos(description);
                break;
            case "D":
                String date = parts[3].trim();
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                task = new Deadlines(description, dateTime);
                break;
            case "E":
                String start = parts[3].trim();
                String end = parts[4].trim();
                LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
                task = new Events(description, startDateTime, endDateTime);
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
