import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private String startDetails;
    private String endDetails;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String task, String startDetails, String endDetails) {
        super(task);
        this.startDetails = startDetails;
        this.endDetails = endDetails;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startDateTime = LocalDateTime.parse(startDetails, formatter);
        this.endDateTime = LocalDateTime.parse(endDetails, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        String formattedStart = startDateTime.format(outputFormatter);
        String formattedEnd = endDateTime.format(outputFormatter);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ") ";
    }
}
