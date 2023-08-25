import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Event extends Task{
    private String startDateTime;
    private String endDateTime;

    public Event(String description, String startDateTime,String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(), startDateTime, endDateTime );
    }


}
