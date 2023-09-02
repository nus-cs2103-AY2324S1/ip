import java.util.*;
import java.time.*;
import java.time.format.*;
public class Event extends Task{
    protected LocalDate by;
    protected LocalDate from;

    public Event(String description, LocalDate from, LocalDate by) {
        super(description);
        this.by = by;
        this.from = from;
    }

    public LocalDate getBy() {
        return this.by;
    }
    public LocalDate getFrom(){
        return this.from;
    }

    public String changeFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateFormatted = date.format(formatter);
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + changeFormat(from) + " "+ "to: " + changeFormat(by) + ")";
    }
}
