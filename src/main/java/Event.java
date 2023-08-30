import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.fromDate = LocalDateTime.parse(this.from, inputFormatter);
        this.toDate = LocalDateTime.parse(this.to, inputFormatter);
    }

    public String getFrom() {
        return this.from;
    }
    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"))
                        + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }

}
