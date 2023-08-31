import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String task, String startDetails, String endDetails) {
        super(task);
        this.startDateTime = Parser.formatDate(startDetails);
        this.endDateTime = Parser.formatDate(endDetails);
    }

    @Override
    public String toString() {
        String formattedStart = Parser.dateToString(startDateTime);
        String formattedEnd = Parser.dateToString(endDateTime);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ") ";
    }
}
