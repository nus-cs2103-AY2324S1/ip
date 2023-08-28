import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return String.format("D%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.by.format(Time.FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(Time.DISPLAY_FORMATTER));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return super.equals(deadline) && this.by.equals(deadline.by);
        }
        return false;
    }
}
