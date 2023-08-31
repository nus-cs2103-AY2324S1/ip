import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "E|" + super.fileSaveFormat() + "|" + from.format(formatter) + "|" + to.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(from) + " to: " + formatDate(to) + ")";
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }
}