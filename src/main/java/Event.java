import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private final Date from;
    private final Date to;

    private final SimpleDateFormat presentationFormat = new SimpleDateFormat("MMM dd yyyy kk");
    private final SimpleDateFormat saveFormat = new SimpleDateFormat("dd/MM/yyyy kk");

    public Event(String name, Date from, Date to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                presentationFormat.format(this.from),
                presentationFormat.format(this.to));
    }

    @Override
    public String toFormat() {
        return String.format(
                "E|%s|%s|%s|%s",
                super.getName(),
                super.isDone() ? "X" : " ",
                saveFormat.format(this.from),
                saveFormat.format(this.to));
    }
}
