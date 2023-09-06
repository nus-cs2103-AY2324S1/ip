import java.time.LocalDate;
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
public class DateTime {
    private LocalDate date;
    private String savedDate;

    public DateTime(String date) {
        this.savedDate = date;
=======
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class DateTime {
    private LocalDate date;
//    private LocalTime time;

    public DateTime(String date) {
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
        this.date = LocalDate.parse(date);
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
<<<<<<< HEAD

    public String getSavedDate() {
        return savedDate;
    }
}

=======
}
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
