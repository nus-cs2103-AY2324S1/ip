import java.util.Date;

public class Event extends Task {
    private final Date from;
    private final Date to;

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
                DateUtils.presentFormat(this.from),
                DateUtils.presentFormat(this.to));
    }

    @Override
    public String toFormat() {
        return String.format(
                "E|%s|%s|%s|%s",
                super.getName(),
                super.isDone() ? "X" : " ",
                DateUtils.saveFormat(this.from),
                DateUtils.saveFormat(this.to));
    }
}
