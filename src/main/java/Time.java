import Exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time {
    private static DateTimeFormatter findDateFormat(String date) throws InvalidDateException {
        DateTimeFormatter[] formats = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("MMM-d-yyyy"),
                DateTimeFormatter.ofPattern("MMM-dd-yyyy"),
                DateTimeFormatter.ofPattern("d-MMM-yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("d-MM-yyyy"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy"),
                DateTimeFormatter.ofPattern("MM-d-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-d"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MMM-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-MMM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("HHmm MMM-d-yyyy"),
                DateTimeFormatter.ofPattern("HHmm MMM-dd-yyyy"),
                DateTimeFormatter.ofPattern("HHmm d-MMM-yyyy"),
                DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("HHmm d-MM-yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM-dd-yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM-d-yyyy"),
                DateTimeFormatter.ofPattern("HHmm yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("HHmm yyyy-MM-d"),
                DateTimeFormatter.ofPattern("HHmm yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MMM d yyyy"),
                DateTimeFormatter.ofPattern("MMM dd yyyy"),
                DateTimeFormatter.ofPattern("d MMM yyyy"),
                DateTimeFormatter.ofPattern("dd MM yyyy"),
                DateTimeFormatter.ofPattern("d MM yyyy"),
                DateTimeFormatter.ofPattern("MM dd yyyy"),
                DateTimeFormatter.ofPattern("MM d yyyy"),
                DateTimeFormatter.ofPattern("yyyy MM dd"),
                DateTimeFormatter.ofPattern("yyyy MM d"),
                DateTimeFormatter.ofPattern("yyyy MM dd"),
                DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"),
                DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd MM yyyy HHmm"),
                DateTimeFormatter.ofPattern("d MM yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM dd yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM d yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM d HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
                DateTimeFormatter.ofPattern("HHmm MMM d yyyy"),
                DateTimeFormatter.ofPattern("HHmm MMM dd yyyy"),
                DateTimeFormatter.ofPattern("HHmm d MMM yyyy"),
                DateTimeFormatter.ofPattern("HHmm dd MM yyyy"),
                DateTimeFormatter.ofPattern("HHmm d MM yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM dd yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM d yyyy"),
                DateTimeFormatter.ofPattern("HHmm yyyy MM dd"),
                DateTimeFormatter.ofPattern("HHmm yyyy MM d"),
                DateTimeFormatter.ofPattern("HHmm yyyy MM dd"),
        };

        for (DateTimeFormatter format: formats) {
            if (hasSameDateFormat(date, format)) {
                return format;
            }
        }
        throw new InvalidDateException();
    }

    private static boolean hasSameDateFormat(String date, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static String FormatDate(String date) throws InvalidDateException {
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        DateTimeFormatter currentFormat = findDateFormat(date);
        return LocalDate.parse(date, currentFormat).format(stdFormat);
    }
}
