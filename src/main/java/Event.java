import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
<<<<<<< HEAD
        try {
            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);
            this.start = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.end = d2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.start = start;
            this.end = end;
        }


=======
        this.start = start;
        this.end = end;
>>>>>>> branch-Level-7
    }
    @Override
    public  String saveString() {
        return "E" + super.saveString() + " | " + this.start + "-" + this.end;
    }
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}