import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) throws DukeException{
        super(description);
        try {
            this.start = formatDate(start);
            this.end = formatDate(end);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your dates in YYYY-MM-DD format!");
        }
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    @Override
    public String save() {
        return "E|" + super.save() + "|" + this.start + "|" + this.end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
