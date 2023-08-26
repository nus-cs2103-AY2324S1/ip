import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String from;
    private String to;
    private LocalDate fromLd;
    private LocalDate toLd;

    private Event(String taskName, String from, String to) throws IncompleteDescriptionException {
        super(taskName);
        try {
            this.fromLd = LocalDate.parse(from);
            this.toLd = LocalDate.parse(to);
        } catch (DateTimeParseException ignored) {}
        this.from = from;
        this.to = to;
    }

    public static Event create(String taskName, String from, String to) throws IncompleteDescriptionException {
        if (from.isBlank() || to.isBlank()) throw new IncompleteDescriptionException();
        return new Event(taskName, from, to);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "E" + " | " + isDoneChar + " | " + this.taskName + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        if (this.fromLd != null && this.toLd != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            return "[E]" + super.toString() + " (from: " + fromLd.format(formatter)
                    + " to: " + toLd.format(formatter) + ")";
        }
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}