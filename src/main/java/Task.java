import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task {
    protected String taskName;
    protected boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getName() {
        return taskName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    public void setNotCompleted() {
        completed = false;
    }

    public String toFileString() {
        if (this instanceof Todo) {
            return "T | " + (completed ? "1" : "0") + " | " + taskName;
        } else if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            return "D | " + (completed ? "1" : "0") + " | " + taskName + " | " + deadline.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (this instanceof Event) {
            Event event = (Event) this;
            return "E | " + (completed ? "1" : "0") + " | " + taskName + " | " + event.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + event.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            return "";
        }
    }

    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0].trim();
        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();

        Task task;
        if (taskType.equals("T")) {
            task = new Todo(taskName);
        } else if (taskType.equals("D")) {
            String by = parts[3].trim();
            task = new Deadline(taskName, LocalDateTime.parse(by));
        } else if (taskType.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            LocalDateTime fromDate = LocalDateTime.parse(from);  // Parse string to LocalDate
            LocalDateTime toDate = LocalDateTime.parse(to);      // Parse string to LocalDate
            task = new Event(taskName, fromDate, toDate); // Create Event with LocalDate objects
        } else {
            task = null;
        }

        if (task != null && isCompleted.equals("1")){
            task.setCompleted();
        }

        return task;
    }

    @Override
    public String toString() {
        char mark;
        if (completed) {
            mark = 'X';
        } else {
            mark = ' ';
        }

        return "[" + mark + "] " + taskName;
    }
}
