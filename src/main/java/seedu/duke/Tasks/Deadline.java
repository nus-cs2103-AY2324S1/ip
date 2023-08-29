package seedu.duke.Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime byDate;
    public Deadline(String description, LocalDateTime byDate, boolean isMarked) {
        super(description, isMarked);
        this.byDate = byDate;
    }

    public String writeFormat() {
        int isDone = super.isMarked() ? 1 : 0;
//        String formattedDate = byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedDate = byDate.format(super.timeFormat);
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