import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                this.description,
                this.start.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
        );
    }

    @Override
    public String toFile() {
        return String.format("E | %s | %s | %s | %s",
                this.isDone ? "1" : "0", this.description, this.start, this.end);
    }

}
