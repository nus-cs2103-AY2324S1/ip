import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that can be completed and described.
 */
public class Task implements Completable, Describable {
    protected String taskName;
    protected boolean completed;
    protected TaskType taskType;

    /**
     * Creates a new task with the given type and name.
     *
     * @param taskType The type of the task (e.g., TODO, DEADLINE, EVENT).
     * @param taskName The name or description of the task.
     */
    public Task(String taskType, String taskName) {
        this.taskType = TaskType.valueOf(taskType);
        this.taskName = taskName;
        this.completed = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String getDescription() {
        return taskName;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the task as completed.
     */
    public void setCompleted() {
        completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void setNotCompleted() {
        completed = false;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Converts the task to a string representation for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
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

    /**
     * Creates a task object from its string representation as stored in a file.
     *
     * @param fileString The string representation of the task as stored in a file.
     * @return A Task object created from the file string, or null if parsing fails.
     */
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

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Generates a string representation of the task.
     *
     * @return A string representation of the task, including its completion status.
     */
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
