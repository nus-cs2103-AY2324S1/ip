package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract task that can be completed and described.
 * This serves as the base class for other specific task types.
 */
public abstract class Task implements Completable, Describable {
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String COMPLETED_MARKER = "1";
    private static final String NOT_COMPLETED_MARKER = "0";

    protected String taskName;
    protected boolean isCompleted;
    protected TaskType taskType;

    public Task(String taskType, String taskName) {
        this.taskType = TaskType.valueOf(taskType.toUpperCase());
        this.taskName = taskName;
        this.isCompleted = false;
    }

    @Override
    public String getDescription() {
        return taskName;
    }

    @Override
    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public void setCompleted() {
        isCompleted = true;
    }

    @Override
    public void setNotCompleted() {
        isCompleted = false;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public abstract String toFileString();

    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        TaskType taskType = TaskType.valueOf(parts[0].trim().toUpperCase());

        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();

        Task task = createTaskBasedOnType(taskType, parts, taskName);
        if (task != null && isCompleted.equals(COMPLETED_MARKER)) {
            task.setCompleted();
        }

        return task;
    }

    public LocalDateTime getDate() {
        return null; // Default implementation returns null
    }

    private static Task createTaskBasedOnType(TaskType taskType, String[] parts, String taskName) {
        switch (taskType) {
            case TODO:
                return new Todo(taskName);
            case DEADLINE:
                return new Deadline(taskName, parseDateTime(parts[3].trim()));
            case EVENT:
                return new Event(taskName, parseDateTime(parts[3].trim()), parseDateTime(parts[4].trim()));
            default:
                return null;
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FILE_DATE_FORMAT);
    }

    @Override
    public String toString() {
        char mark = isCompleted ? 'X' : ' ';
        return "[" + mark + "] " + taskName;
    }
}