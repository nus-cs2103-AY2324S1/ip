package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class which consist of the detail needed for a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor for this class.
     *
     * @param description String for description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get the status of the task.
     *
     * @return String to should the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * unmark the task
     */
    public void unMark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * convert task to string to save to file
     *
     * @return task in String format
     */
    public String toFileFormat() {
        String done = isDone ? "1" : "0";
        return String.format("T | %s | %s", done, description);
    }

    /**
     * Read the task saved in file which is in String format and convert it back as task form
     *
     * @param line the line of task
     * @return task in Task format
     */
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
