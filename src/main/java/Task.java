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
     * Convert the task to its file format representation.
     * @return String representation of task for file storage.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Convert a file format string back to a Task.
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