import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalTime;
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public LocalDate convertToDate(String dates) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate date = LocalDate.parse(dates, inputFormatter);
        return date;
    }
    public LocalDateTime convertToDateTime(String dates) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dates, inputFormatter);
        return dateTime;
    }

    public LocalTime convertToTime(String times) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(times, inputFormatter);
        return time;
    }

    @Override
    public String toString() {
        String startDate;
        String endDate;
        String pattern1 = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}";
        String pattern2 = "\\d{4}-\\d{1,2}-\\d{1,2}";
        String pattern3 = "\\d{4}";
        Pattern regexPattern1 = Pattern.compile(pattern1);
        Pattern regexPattern2 = Pattern.compile(pattern2);
        Pattern regexPattern3 = Pattern.compile(pattern3);

        Matcher startMatcher1 = regexPattern1.matcher(from);
        Matcher startMatcher2 = regexPattern2.matcher(from);
        Matcher startMatcher3 = regexPattern3.matcher(from);

        Matcher endMatcher1 = regexPattern1.matcher(to);
        Matcher endMatcher2 = regexPattern2.matcher(to);
        Matcher endMatcher3 = regexPattern3.matcher(to);

        if (startMatcher1.matches()) {
            LocalDateTime dateTime = convertToDateTime(from);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            startDate = dateTime.format(outputFormatter);
        } else if (startMatcher2.matches()){
            LocalDate date = convertToDate(from);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            startDate = date.format(outputFormatter);
        } else if (startMatcher3.matches()){
            LocalTime time = convertToTime(from);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");
            startDate = time.format(outputFormatter);
        } else {
            startDate = from;
        }

        if (endMatcher1.matches()) {
            LocalDateTime dateTime = convertToDateTime(to);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            endDate = dateTime.format(outputFormatter);
        } else if (endMatcher2.matches()){
            LocalDate date = convertToDate(to);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            endDate = date.format(outputFormatter);
        } else if (endMatcher3.matches()){
            LocalTime time = convertToTime(to);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");
            endDate = time.format(outputFormatter);
        } else {
            endDate = to;
        }
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
