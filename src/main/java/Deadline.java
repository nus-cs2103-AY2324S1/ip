import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    String stringToFile() {
        return String.format("D | %s | %s", super.stringToFile(),
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
