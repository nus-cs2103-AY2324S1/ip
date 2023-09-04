import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class DateTime {
    private LocalDate date;
//    private LocalTime time;

    public DateTime(String date) {
        this.date = LocalDate.parse(date);
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
