import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Task {
    protected TaskType type;
    protected String description;
    protected boolean isDone;

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

    public LocalDateTime convertToLocalDateTime(String s) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(s, formatter);
    }

    public String convertDateTimeToString(LocalDateTime dt) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", Locale.ENGLISH);
        return dt.format(formatter);
    }
}
