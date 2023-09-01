import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Deadline extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    protected LocalDate by;
    public Deadline(String description, String by) throws FlukeException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, boolean isDone, String by) throws FlukeException {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        String byString = by.format(DATE_TIME_FORMATTER);
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
