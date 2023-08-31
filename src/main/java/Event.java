import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    protected Date from;
    protected Date to;

    public Event(String description, Date from, Date to) {
        super(description);
//        if (description.isEmpty())
//            throw new DukeException("☹ OOPS!!! The description / from / to of a event cannot be empty.");
        this.from = from;
        this.to = to;
    }

    @Override
    public String toWrite() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "E | " + super.toWrite() + " | " + formatter.format(from) + "-" + formatter.format(to) + "\n";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + formatter.format(from) + " to: " + formatter.format(to) + ")";
    }
}
