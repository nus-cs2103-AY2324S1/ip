import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Deadline extends Task {
//    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String date, String time) {
        super(description);
//        this.by = by;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }

    public String reformatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public String reformatTime() {
        return this.time.format(DateTimeFormatter.ofPattern("h.mma", Locale.US));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + reformatDate() + " " + reformatTime() + ")";
    }

    @Override
    public String fileDescription() {
        return "D" + super.fileDescription() + " | " + "by " + reformatDate() + " " + reformatTime() + "\n";
    }
}
