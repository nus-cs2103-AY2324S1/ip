import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;
    static DateTimeFormatter formatterToTxtString = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = getDateTime(by);
    }
    public static LocalDateTime getDateTime(String dateTime) {
//        String[] datesPart = dateTime.split(" "); // 2/12/2019 1800 -> {2/12/2019, 1800}   2019-12-02 1800
//        LocalDate lt = LocalDate.parse(datesPart[0]);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatterToTxtString);
    }
    @Override
    public String toTxtString() {
//        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + by;

        return "[D] | [" + (this.isDone ? "X": " ") + "] | " + this.description + " | "
                + this.dateTime.format(formatterToTxtString);
    }
    @Override
    public String toString() {
//        return "[D]" + super.toString() + " (by: " + by + ")";
        DateTimeFormatter formatterToString = DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(formatterToString) + ")";
    }
}
