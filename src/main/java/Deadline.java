import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalTime;

public class Deadline extends Task {
    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public LocalDate convertToDate(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate date = LocalDate.parse(by, inputFormatter);
        return date;
    }
    public LocalDateTime convertToDateTime(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormatter);
        return dateTime;
    }

    public LocalTime convertToTime(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(by, inputFormatter);
        return time;
    }

    @Override
    public String toString() {
        String formattedDate;
        String pattern1 = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}";
        String pattern2 = "\\d{4}-\\d{1,2}-\\d{1,2}";
        String pattern3 = "\\d{4}";
        Pattern regexPattern1 = Pattern.compile(pattern1);
        Pattern regexPattern2 = Pattern.compile(pattern2);
        Pattern regexPattern3 = Pattern.compile(pattern3);
        Matcher matcher1 = regexPattern1.matcher(by);
        Matcher matcher2 = regexPattern2.matcher(by);
        Matcher matcher3 = regexPattern3.matcher(by);

        if (matcher1.matches()) {
            LocalDateTime byDateTime = convertToDateTime(by);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            formattedDate = byDateTime.format(outputFormatter);
        } else if (matcher2.matches()){
            LocalDate byDate = convertToDate(by);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            formattedDate = byDate.format(outputFormatter);
        } else if (matcher3.matches()) {
            LocalTime time = convertToTime(by);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");
            formattedDate = time.format(outputFormatter);
        } else {
            formattedDate = by;
        }

        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
