import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String name, String from, String to) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a Task cannot be empty.");
        }
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Follow the format for an Event.");
        }
    }

    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname() + ":" + this.from + ":" + this.to;
        } else {
            return "T : 0 : " + this.getname() + ":" + this.from + ":" + this.to;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + this.from.format(format) +
                ") (to: " + this.to.format(format) + ")";
    }
}
