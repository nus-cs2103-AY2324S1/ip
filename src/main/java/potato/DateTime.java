package potato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateTime {
    private LocalDate date;
    private String savedDate;

    public DateTime(String date) {
        this.savedDate = date;
        this.date = LocalDate.parse(date);
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getSavedDate() {
        return savedDate;
    }
}
