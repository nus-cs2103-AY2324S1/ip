import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {

    protected LocalDate date;
    protected LocalTime time;
    public Date(String dateString, String timeString) {
        String str = "2016-03-04 11:30";
        date = dateString == null ? null : LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        time = timeString == null ? null : LocalTime.parse(timeString, formatter);
    }

    @Override
    public String toString() {
        String dateString = date == null ? "" : date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeString = time == null ? "" : time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return dateString + " " + timeString;
    }

    public String toSave() {
        String dateString = date == null ? "" : date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeString = time == null ? "" : time.format(DateTimeFormatter.ofPattern("HH:mm"));
        return dateString + " " + timeString;
    }
}
