import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String description1;
    private String description2;

    LocalDateTime startDate;
    LocalDateTime endDate;

    public Event(String name, String description1, String description2) {
        super(name);
        this.description1 = description1;
        this.description2 = description2;
    }

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String detail1 = description1 == null
                ? startDate.format(DateTimeFormatter.ofPattern("hh':'mma',' d MMM uuuu',' eee"))
                : description1;
        String detail2 = description2 == null
                ? endDate.format(DateTimeFormatter.ofPattern("hh':'mma',' d MMM uuuu',' eee"))
                : description2;
        return "[E]" + super.toString() + " (from: " + detail1 + " to: " + detail2 + ")";
    }

}
