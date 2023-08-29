import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.date = LocalDateTime.parse(date, formatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
    }

    @Override
    public String formatData() {
        return String.format("D | %d | %s | %s\n", this.isDone ? 1 : 0,
                this.description, this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}