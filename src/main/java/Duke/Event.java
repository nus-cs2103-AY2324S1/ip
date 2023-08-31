package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate timestart;
    LocalDate timeend;
    public Event(String name, LocalDate timestart, LocalDate timeend) {
        super(name);
        this.timestart = timestart;
        this.timeend = timeend;
    }

    public String toString() {
        return ("[E]" + super.toString() + " (from: " + timestart.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + timeend.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
