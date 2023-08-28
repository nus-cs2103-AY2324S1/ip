import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DateTimeHandler {
    private LocalDateTime date;
    private String dateTimeString;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    public DateTimeHandler(String s) throws InvalidTaskException {
        try {
            String[] dateTime = s.split(" ");
            String[] dateString = dateTime[0].split("/");
            int timeString = Integer.parseInt(dateTime[1]);
            day = Integer.parseInt(dateString[0]);
            month = Integer.parseInt(dateString[1]);
            year = Integer.parseInt(dateString[2]);
            hour = timeString/100;
            min = timeString%100;
            date = LocalDateTime.of(year,month,day,hour,min);
            dateTimeString = date.getMonth() +  " " + date.getDayOfMonth() + " " + date.getYear();
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new InvalidTaskException("That's not a proper deadline BAAAKKKAAAAAAA!!!!!", true);
        } catch (DateTimeException exception) {
            throw new InvalidTaskException("That's not a proper deadline BAAAKKKAAAAAAA!!!!!", true);
        }
    }

    public LocalDateTime getDate() {
        return date;
    }
    public String stringDisplay() {
        return dateTimeString + " " + ((hour < 10) ? "0" + hour : hour) + ":" + ((min < 10) ? "0" + min : min);
    }
    public String toString() {
        return day + "/" + month + "/" + year + " " + ((hour < 10) ? "0" + hour : hour) + ((min < 10) ? "0" + min : min);
    }
}
