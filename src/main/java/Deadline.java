import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDateTime dueDate;
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        String[] dateYearMonthDay = dueDate.split(" ")[0].split("-");
        String[] timeHourMinute = dueDate.split(" ")[1].split(":");
        this.dueDate = LocalDateTime.of(Integer.parseInt(dateYearMonthDay[0]),
                Integer.parseInt(dateYearMonthDay[1]),
                Integer.parseInt(dateYearMonthDay[2]),
                Integer.parseInt(timeHourMinute[0]),
                Integer.parseInt(timeHourMinute[1]));
    }

    @Override
    public String toMemoryFormat() {
        return String.format("D | %s | %s | %s", (super.isDone ? "1" : "0"),
                super.taskName, this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (super.isDone ? "X" : " "), super.taskName,
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
