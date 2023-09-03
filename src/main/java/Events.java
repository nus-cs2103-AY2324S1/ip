import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    final String taskChar = "[E]";
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;
    protected String dateString = "";

    public Events(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        setDates(startDate, startTime, endDate, endTime);
    }

    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setDates(LocalDate start, LocalTime startTime, LocalDate end, LocalTime endTime) {
        this.startDate = start;
        this.endDate = end;
        this.startTime = startTime;
        this.endTime = endTime;
        String startDateTime = formatDate(this.startDate) + " " + formatTime(this.startTime);
        String endDateTime = formatDate(this.endDate) + " " + formatTime(this.endTime);
        this.dateString = " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.getStatusNumber() + " | " + this.taskName + " | " + this.dateString;
    }

    @Override
    public String toString() {
        return taskChar + super.toString() + this.dateString;
    }
}
