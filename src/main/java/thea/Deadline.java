package thea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * This class has dueDate which is the characteristic of a Deadline task.
 * This class is a subclass of the abstract class Task.
 */
public class Deadline extends Task{
    LocalDateTime dueDate;

    /**
     * Constructs a new Deadline object.
     * Converts the dueDate from String to LocalDateTime.
     *
     * @param taskName description of the task.
     * @param dueDate dueDate in String.
     */
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

    /**
     * Returns the task in memory format, which means the format
     * of which the task is saved to the hard disk.
     *
     * @return task in memory format.
     */
    @Override
    public String toMemoryFormat() {
        return String.format("D | %s | %s | %s", (super.isDone ? "1" : "0"),
                super.taskName, this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * Returns the task in desired string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (super.isDone ? "X" : " "), super.taskName,
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
