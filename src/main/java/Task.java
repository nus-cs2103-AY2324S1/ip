import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task implements Completable, Describable {
    protected String taskName;
    protected boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    @Override
    public String getDescription() {
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
            try {
                LocalDateTime deadlineDateTime = parseDateTime(by);
                task = new Deadline(taskName, deadlineDateTime);
            } catch (DateTimeParseException e) {
                task = null;
            }
        } else if (taskType.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            try {
                LocalDateTime fromDate = parseDateTime(from);
                LocalDateTime toDate = parseDateTime(to);
                task = new Event(taskName, fromDate, toDate);
            } catch (DateTimeParseException e) {
                task = null;
            }
        } else {
            task = null;
        }

        if (task != null && isCompleted.equals("1")){
            task.setCompleted();
        }

        return task;
    }

    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
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
