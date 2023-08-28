package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task  {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toFileString() {
        String type = "";
        String dateTime = "";

        if (this instanceof Todo) {
            type = "T";
        } else if (this instanceof Deadline) {
            type = "D";
            dateTime = " | " + ((Deadline) this).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (this instanceof Event) {
            type = "E";
            dateTime = " | " + ((Event) this).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " +
                    ((Event) this).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return type + " | " + (isDone ? "1" : "0") + " | " + description + dateTime;
    }


    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\s*\\|\\s*"); // Adjusted delimiter
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                // Use parseDate method to convert to LocalDate
                task = new Deadline(description, parseDate(by));
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                // Use parseDateTime method to convert to LocalDateTime
                task = new Event(description, parseDateTime(from), parseDateTime(to));
                break;
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }

        if (isDone) {
            task.mark();
        }

        return task;
    }

    private static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    private static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getDescription();

    }
}
