import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private LocalDateTime byDate;
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    public String changeFormat() {
        int isDone = isMarked ? 1 : 0;
        String formattedDate = byDate.format(DateTimeFormatter.ofPattern("dd MMM HH:mm"));
        return "D" + " | " + isDone + " | " + super.description + " | " + formattedDate;
    }

    public LocalDate getByDate() {
        return this.byDate.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}