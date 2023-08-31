import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate dateBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void stringToDate() {
        Pattern datePattern = Pattern.compile(
                "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
        Matcher dateTimeMatcher = datePattern.matcher(this.by);

        if (!dateTimeMatcher.matches()) { // if datetime doesn't match, do nothing
            this.dateBy = null;
        } else {
            Pattern dateTimePattern2 = Pattern.compile(
                    "(\\d{4})-(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])"); // YYYY-MM-DD
            Matcher dateTimePattern2Matcher = dateTimePattern2.matcher(this.by);
            dateTimePattern2Matcher.matches();
            int day = Integer.parseInt(dateTimePattern2Matcher.group(3));
            int month = Integer.parseInt(dateTimePattern2Matcher.group(2));
            int year = Integer.parseInt(dateTimePattern2Matcher.group(1));
            this.dateBy = LocalDate.of(year, month, day);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (dateBy == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            String formattedDate = this.dateBy.format(formatter);
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        }
    }
}
