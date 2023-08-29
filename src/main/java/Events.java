import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    private LocalDateTime start;
    private LocalDateTime end;

    Events(LocalDateTime start, LocalDateTime end, String description) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getExact() {
        String mmmDdYyyy = "";
        if (start.toLocalDate().equals(end.toLocalDate())) {
             mmmDdYyyy = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " +
                    start.format(DateTimeFormatter.ofPattern("hh:mm a")) + " " +
                    end.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {
             mmmDdYyyy =  start.format(DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy"))
                     + " to " + end.format(DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy"));
        }
        return mmmDdYyyy;

        }


    @Override
    public String toString() {
        return "[E]" + super.toString().substring(3) + " (from: " + getExact() + ")";
    }

}
