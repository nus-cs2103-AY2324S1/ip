import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringToDateTime {
    protected String dateString;

    public StringToDateTime(String dateString) {
        this.dateString = dateString;
    }

    public LocalDateTime converter() {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MMM-dd h:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd MMM yyyy")  // month short forms
        };

        LocalDateTime dateTime = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                dateTime = LocalDateTime.parse(dateString, formatter);
                break; // Break if parsing is successful with a formatter
            } catch (DateTimeException e) {
                try {
                    dateTime = LocalDate.parse(dateString, formatter).atStartOfDay();
                    break; // Break if parsing is successful with a formatter
                } catch (Exception f) {
                    try {
                        String[] parts = dateString.split(" ");
                        String temp = parts[0].trim() + " " + parts[1].trim().substring(0, 3) + " " + parts[2].trim();

                        dateTime = LocalDate.parse(temp, formatter).atStartOfDay();
                        break; // Break if parsing is successful with a formatter
                    } catch (Exception g) {
                        // Ignore and move on to next 
                    }
                }
            }
        }

        return dateTime;
    }
}