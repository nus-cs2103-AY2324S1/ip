import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String taskDescription;
    protected LocalDateTime from;
    protected LocalTime to;
    private String identifier;


    public Event(String taskDescription, LocalDateTime from, LocalTime to, boolean isDone) {
        super(taskDescription, isDone);
        this.from = from;
        this.to = to;
        this.identifier = "[E]";
    }
    //System.out.println(dateTime.format(DateTimeFormatter.ofPattern("LLL dd yyyy Ka")));

    public String toString() {
        return this.identifier + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("LLL dd yyyy Ka")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("Ka")) + ")";
    }
}
