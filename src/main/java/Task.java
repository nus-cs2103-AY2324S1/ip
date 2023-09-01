import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task implements Completable, Describable {
    protected String taskName;
    protected boolean completed;
    protected TaskType taskType;

    public Task(String taskType, String taskName) {
        this.taskType = TaskType.valueOf(taskType);
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

    public TaskType getTaskType() {
        return taskType;
    }

    public String toFileString() {
        String taskTypeString = taskType.toString();
        if (taskType == TaskType.TODO) {
            return taskTypeString + " | " + (completed ? "1" : "0") + " | " + taskName;
        } else if (taskType == TaskType.DEADLINE) {
            Deadline deadline = (Deadline) this;
            return taskTypeString + " | " + (completed ? "1" : "0") + " | " + taskName + " | " + deadline.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (taskType == TaskType.EVENT) {
            Event event = (Event) this;
            return taskTypeString + " | " + (completed ? "1" : "0") + " | " + taskName + " | " + event.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + event.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            return "";
        }
    }

    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        TaskType taskType = TaskType.valueOf(parts[0].trim());

        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();

        Task task;
        if (taskType == TaskType.TODO) {
            task = new Todo(taskName);
        } else if (taskType == TaskType.DEADLINE) {
            String by = parts[3].trim();
            LocalDateTime deadlineDateTime = parseDateTime(by);
            task = new Deadline(taskName, deadlineDateTime);
        } else if (taskType == TaskType.EVENT) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            LocalDateTime fromDateTime = parseDateTime(from);
            LocalDateTime toDateTime = parseDateTime(to);
            task = new Event(taskName, fromDateTime, toDateTime);
        } else {
            task = null;
        }

        if (task != null && isCompleted.equals("1")) {
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
