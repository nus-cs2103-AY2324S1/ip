import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

abstract class Task {
    protected String description;
    protected boolean isDone;

    abstract public String getDataString();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String getDescription() {
        return this.description;
    }

    protected String formatDateTime(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy, KK:mma");
            return formatter.format(temporalAccessor);
        }

        if (temporalAccessor instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy");
            return formatter.format(temporalAccessor);
        }

        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}