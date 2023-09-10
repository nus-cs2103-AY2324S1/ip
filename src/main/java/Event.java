import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Event(String name, String startTime, String endTime) {
        this.task_name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.endTime = LocalDateTime.parse(endTime, formatter);
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String desiredStartFormat = this.startTime.format(formatter);
        String desiredEndFormat = this.endTime.format(formatter);
        return "[E]" + super.toString() + "(from: " + desiredStartFormat + " to: " + desiredEndFormat + ")";
    }
}
