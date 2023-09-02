import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        String[] dateYearMonthDayFrom = from.split(" ")[0].split("-");
        String[] timeHourMinuteFrom = from.split(" ")[1].split(":");
        String[] dateYearMonthDayTo = to.split(" ")[0].split("-");
        String[] timeHourMinuteTo = to.split(" ")[1].split(":");
        this.from = LocalDateTime.of(Integer.parseInt(dateYearMonthDayFrom[0]),
                Integer.parseInt(dateYearMonthDayFrom[1]),
                Integer.parseInt(dateYearMonthDayFrom[2]),
                Integer.parseInt(timeHourMinuteFrom[0]),
                Integer.parseInt(timeHourMinuteFrom[1]));
        this.to = LocalDateTime.of(Integer.parseInt(dateYearMonthDayTo[0]),
                Integer.parseInt(dateYearMonthDayTo[1]),
                Integer.parseInt(dateYearMonthDayTo[2]),
                Integer.parseInt(timeHourMinuteTo[0]),
                Integer.parseInt(timeHourMinuteTo[1]));
    }

    @Override
    public String toMemoryFormat() {
        return String.format("E | %s | %s | %s | %s", (super.isDone ? "1" : "0"),
                super.taskName, this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                (super.isDone ? "X" : " "), super.taskName,
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
