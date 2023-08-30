import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Task {
    private String description;
    private boolean isDone;
    private static final List<DateTimeFormatter> DATE_FORMATTERS = new ArrayList<>();

    private static final List<DateTimeFormatter> DAY_FORMATTERS = new ArrayList<>();
    private static final List<DateTimeFormatter> TIME_FORMATTERS = new ArrayList<>();

    static {
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy/MM/d"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-d"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("d-MM-yyyy"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("d.MM.yyyy"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy.MM.d"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("d.MM.yyyy"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("MMM d yyyy"));
        DATE_FORMATTERS.add(DateTimeFormatter.ofPattern("d MMM yyyy"));

        DAY_FORMATTERS.add(DateTimeFormatter.ofPattern("E", Locale.ENGLISH));
        DAY_FORMATTERS.add(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH));

        TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("hmma",Locale.ENGLISH));
        TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("ha",Locale.ENGLISH));
        TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("h.mma",Locale.ENGLISH));

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private String fileGetStatusIcon(){
        return (isDone ? "Y" : "N");
    }

    private String getDescription(){
        return description;
    }

    public void taskDone(){
        isDone = true;
    }

    public void taskStatusFromFile(boolean status){
        isDone = status;
    }

    public void undoTask() {
        isDone = false;
    }

    public LocalDate convertStringToDate(String date) {
            for (DateTimeFormatter formatter : DATE_FORMATTERS) {
                try {
                    return LocalDate.parse(date, formatter);
                } catch (Exception ignored) {
                }
            }
        throw new IllegalArgumentException("Unrecognised date format: " + date);
    }

    public DayOfWeek convertStringToDay(String day) {
        for (DateTimeFormatter formatter : DAY_FORMATTERS) {
            try {
                return DayOfWeek.from(formatter.parse(day));
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Unrecognised day format: " + day);
    }

    public LocalTime convertStringToTime(String time) {
        for (DateTimeFormatter formatter : TIME_FORMATTERS) {
            try {
                return LocalTime.parse(time, formatter);
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Unrecognised time format: " + time);
    }
    @Override
    public String toString(){
        return " [" + getStatusIcon() + "] " + getDescription();
    }

    public String toFileString(){
        return "|" + fileGetStatusIcon() + "|" + getDescription();
    }
}
