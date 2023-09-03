import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime startDate;
    LocalDateTime endDate;
    //Introducing LocalDateTime to parse the string inputs given by the user
    //for the relevant date and time

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Event(String description, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String taskString() {
        return "[E]" + super.taskString() + "(from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
    @Override
    public String saveTaskString() {
        String status = (this.isDone ? "1" : "0");
        return "E" + "|" + status + "|" + this.description
                + "|" + this.startDate + "|" + this.endDate;
    }
}