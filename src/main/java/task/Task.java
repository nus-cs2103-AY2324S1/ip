package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract task that can be completed and described.
 * This serves as the base class for other specific task types.
 */
public abstract class Task implements Completable, Describable {
    /** The name or description of the task. */
    protected String taskName;

    /** The completion status of the task. */
    protected boolean completed;

    /** The type of the task, e.g., TODO, DEADLINE, EVENT. */
    protected TaskType taskType;

    /**
     * Constructs a Task object with the given task type and name.
     *
     * @param taskType The type of the task.
     * @param taskName The name or description of the task.
     */
    public Task(String taskType, String taskName) {
        this.taskType = TaskType.valueOf(taskType);
        this.taskName = taskName;
        this.completed = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String getDescription() {
        return taskName;
    }

    /**
     * Checks the completion status of the task.
     *
     * @return True if the task is completed; false otherwise.
     */
    @Override
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the task as completed.
     */
    @Override
    public void setCompleted() {
        completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    @Override
    public void setNotCompleted() {
        completed = false;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Represents the task in a string format suitable for file storage.
     *
     * @return A formatted string representing the task for file purposes.
     */
    public abstract String toFileString();

    /**
     * Constructs a Task object from its string representation intended for file storage.
     *
     * @param fileString The formatted string representing the task.
     * @return A Task object derived from the string, or null if the parsing fails.
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
     * Converts a date and time string into its LocalDateTime representation.
     *
     * @param dateTimeString The formatted date-time string.
     * @return A LocalDateTime object derived from the string.
     */
    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Provides a formatted string representation of the task, displaying its completion status.
     *
     * @return A string detailing the task and its completion status.
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
