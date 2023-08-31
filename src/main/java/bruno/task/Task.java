package bruno.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Superclass for all task types.
 */
public class Task {
    protected TaskType type;
    protected String description;
    private boolean isDone;

    /**
     * Creates a new instance of the Task class.
     * @param type The task type.
     * @param description Description of the task.
     */
    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getFileString() {
        return (isDone ? "✅" : "⭕️") + "|" + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    /**
     * Converts the string into instance of LocalDateTime.
     * @param s The input string.
     * @return LocalDateTime instance for the converted string.
     * @throws DateTimeException
     */
    public LocalDateTime convertToLocalDateTime(String s) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(s, formatter);
    }

    /**
     * Converts the LocalDateTime instance into a string.
     * @param dt LocalDateTime instance to be converted.
     * @return String representation of the LocalDateTime instance.
     * @throws DateTimeException
     */
    public String convertDateTimeToString(LocalDateTime dt) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", Locale.ENGLISH);
        return dt.format(formatter);
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}
