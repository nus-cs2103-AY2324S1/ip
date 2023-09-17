package martin.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *   
     * @param keyword The keyword to check against the task's description.
     * @return true if the task's description contains the keyword, otherwise false.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Snoozes or postpones the task by the given duration.
     * 
     * @param duration The amount of time to snooze or postpone the task.
     * @throws UnsupportedOperationException if the task type does not support snoozing.
     */
    public void snooze(Duration duration) {
        throw new UnsupportedOperationException("This task type cannot be snoozed.");
    }

    /**
     * Converts the task to its corresponding file format representation.
     * 
     * @return A string representation of the task suitable for file storage.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts a file format string back to a Task.
     * 
     * @param fileFormatString String representation from file.
     * @return A new Task instance.
     */
    public static Task fromFileFormat(String fileFormatString) {
        String[] parts = fileFormatString.split("\\s*\\|\\s*");
    
        String type = parts[0];
    
        switch (type) {

            case "D": // Deadline tasks
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid Deadline format in file.");
                }
            
                boolean isDoneDeadline = "1".equals(parts[1]);
                String descriptionDeadline = parts[2];
                LocalDateTime by = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            
                Deadline deadline = new Deadline(descriptionDeadline, by);
                if (isDoneDeadline) {
                    deadline.markAsDone();
                }

                return deadline;

            case "E":
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Invalid Event format in file.");
                }

                boolean isDoneEvent = "1".equals(parts[1]);
                String descriptionEvent = parts[2];
                LocalDateTime from = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                LocalDateTime to = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

                Event event = new Event(descriptionEvent, from, to);
                if (isDoneEvent) {
                    event.markAsDone();
                }

                return event;

            default:
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid task format in file.");
                }
    
                boolean isDone = "1".equals(parts[0]);
                String description = parts[1];
                Task task = new Task(description);
                if (isDone) {
                    task.markAsDone();
                }
    
                return task;
        }
    }


    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}