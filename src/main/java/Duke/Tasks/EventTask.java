package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private String from = "";
    private String to = "";

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public EventTask(String itemName, String from, String to) {
        super(itemName);
        this.from = from;
        this.to = to;
    }

    public EventTask(String itemName, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(itemName);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }


    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String encodeTask() {
        return super.encodeTask() + " | " + this.fromDateTime + " | " + this.toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) +
                " to: " + this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
