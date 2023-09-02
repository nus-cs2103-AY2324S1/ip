import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    private LocalDateTime date;
    public DateTimeHandler (String s) {
        if (!s.contains("/")) { // reading from 'tasklist.txt', change format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy K:mma");
            this.date = LocalDateTime.parse(s, formatter);
        } else {
            String[] temp = s.split(" ");
            // date
            String[] dateSplit = temp[0].split("/");
            int day = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            int year = Integer.parseInt(dateSplit[2]);
            // time
            int hour = Integer.parseInt(temp[1]) / 100;
            int min = Integer.parseInt(temp[1]) % 100;

            this.date = LocalDateTime.of(year, month, day, hour, min);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy K:mma");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }
}
