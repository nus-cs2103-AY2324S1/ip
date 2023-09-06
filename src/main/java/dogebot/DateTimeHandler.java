package dogebot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    private LocalDateTime date;
    public DateTimeHandler(String s) {
        int day, month, year, hour, min;
        String[] temp = s.split(" ");

        if (!s.contains("/")) { // reading from 'tasklist.txt', change format
            day = Integer.parseInt(temp[0]);
            month = monthToInt(temp[1]);
            year = Integer.parseInt(temp[2]);
            hour = Integer.parseInt(temp[3].substring(0, 1));
            if (temp[3].substring(temp[3].length() - 2, temp[3].length()).equals("PM")) {
                if (hour + 12 == 24) {
                    hour = 00;
                } else {
                    hour += 12;
                }
            }
            min = Integer.parseInt(temp[3].substring(2, 4));
        } else {
            // date
            String[] dateSplit = temp[0].split("/");
            day = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            year = Integer.parseInt(dateSplit[2]);
            // time
            hour = Integer.parseInt(temp[1]) / 100;
            min = Integer.parseInt(temp[1]) % 100;
        }
        this.date = LocalDateTime.of(year, month, day, hour, min);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy K:mma");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }

    public int monthToInt(String s) {
        int month;
        switch (s) {
        case "Jan":
            month = 1;
            break;
        case "Feb":
            month = 2;
            break;
        case "Mar":
            month = 3;
            break;
        case "Apr":
            month = 4;
            break;
        case "May":
            month = 5;
            break;
        case "Jun":
            month = 6;
            break;
        case "Jul":
            month = 7;
            break;
        case "Aug":
            month = 8;
            break;
        case "Sep":
            month = 9;
            break;
        case "Oct":
            month = 10;
            break;
        case "Nov":
            month = 11;
            break;
        case "Dec":
            month = 12;
            break;
        default:
            month = 0;
            break;
        }
        return month;
    }
}
