package duke.task;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String fileGetStatusIcon(){
        return (isDone ? "Y" : "N");
    }

    public String getDescription(){
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
        System.out.println("Unrecognised date format: " + date +
                "\nPlease key in date in format D/MM/YYYY");
        return null;
    }

    public DayOfWeek convertStringToDay(String day) {
        for (DateTimeFormatter formatter : DAY_FORMATTERS) {
            try {
                return DayOfWeek.from(formatter.parse(day));
            } catch (Exception ignored) {
            }
        }
        System.out.println("Unrecognised day format: " + day +
                "\nPlease key in day in format Mon OR Monday " +
                "\nThe task has been stored without a dueday, remove task and key in correct format to make changes \n");
        return null;
    }

    public LocalTime convertStringToTime(String time) {
        for (DateTimeFormatter formatter : TIME_FORMATTERS) {
            try {
                return LocalTime.parse(time, formatter);
            } catch (Exception ignored) {
            }
        }
        System.out.println("Unrecognised time format: " + time +
                "\nPlease key in time in format Hr.MinAM/PM " +
                "\nThe task has been stored without a due time, remove task and key in correct format to make changes \n");
        return null;
    }

    public String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
    public String dayToString(DayOfWeek day) {
        if(day!=null) {
            return ", " + day.name().substring(0,1) + day.name().substring(1).toLowerCase() ;
        } else {
            return "";
        }
    }

    public String timeToString(LocalTime time) {
        if(time!=null) {
            return ", " + time.format(DateTimeFormatter.ofPattern("h.mma")).toUpperCase();
        } else {
            return "";
        }
    }
    @Override
    public String toString(){
        return " [" + getStatusIcon() + "] " + getDescription();
    }

    public String toFileString(){
        return "|" + fileGetStatusIcon() + "|" + getDescription();
    }
}
