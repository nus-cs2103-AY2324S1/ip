package duke.task;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The Task class represents a task with a description and completion status.
 * It provides methods for managing task details, status and conversions.
 */
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

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The String representation of the task's completion status.
     *
     * @return The Sting "X" if task completed, " " if not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * The String representation of the task's completion status when tasks are stored.
     *
     * @return The Sting "Y" if task completed, "N" if not completed.
     */
    public String fileGetStatusIcon(){
        return (isDone ? "Y" : "N");
    }

    private String getDescription(){
        return description;
    }

    /**
     * Marks task's completion status as done.
     */
    public void taskDone(){
        isDone = true;
    }

    /**
     * Retrieves tasks status from file.
     */
    public void taskStatusFromFile(boolean status){
        isDone = status;
    }

    /**
     * Marks task's completion status as undone.
     */
    public void undoTask() {
        isDone = false;
    }

    /**
     * Converts a string representation to a LocalDate object.
     *
     * @param date The date string to be converted.
     * @return The converted LocalDate object
     */
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

    /**
     * Converts a string representation to a DayOfWeek object.
     *
     * @param day The day string to be converted.
     * @return The converted DayOfWeek object.
     */
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

    /**
     * Converts a string representation to a LocalTime object.
     *
     * @param time The time string to be converted.
     * @return The converted LocalTime object.
     */
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

    /**
     * Converts a LocalDate object to a formatted date string.
     *
     * @param date The LocalDate object to be converted.
     * @return The converted date String.
     */
    public String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Converts a DayToWeek object to a formatted day string.
     *
     * @param day The DayToWeek object to be converted.
     * @return The converted day String.
     */
    public String dayToString(DayOfWeek day) {
        if(day!=null) {
            return ", " + day.name().substring(0,1) + day.name().substring(1).toLowerCase() ;
        } else {
            return "";
        }
    }

    /**
     * Converts a LocalTime object to a formatted day string.
     *
     * @param time The LocalTime object to be converted.
     * @return The converted time String.
     */
    public String timeToString(LocalTime time) {
        if(time!=null) {
            return ", " + time.format(DateTimeFormatter.ofPattern("h.mma")).toUpperCase();
        } else {
            return "";
        }
    }

    /**
     * Converts the task to a formatted string representation to be displayed to users.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString(){
        return " [" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Converts the task to a formatted string representation for file storage.
     *
     * @return The formatted string representation of the task for file storage.
     */
    public String toFileString(){
        return "|" + fileGetStatusIcon() + "|" + getDescription();
    }
}
