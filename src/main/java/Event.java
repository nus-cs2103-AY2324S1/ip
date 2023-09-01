import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final long serialVersionUID = -4674004457612195474L;
    //included serialVersionUID indicated as the Task implements Serializable Interface
    LocalDateTime startDate;
    LocalDateTime endDate;
    //Introducing LocalDateTime to parse the string inputs given by the user
    //for the relevant date and time

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String taskString() {
        return "[E]" + super.taskString() + "(from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + "to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}