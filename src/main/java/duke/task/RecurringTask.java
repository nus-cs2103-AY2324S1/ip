package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a recurring date.
 */
public class RecurringTask extends Task {
    /**
     * Represents time in HH:mm.
     */
    private String time;

    /**
     * Constructs Recurring task with the current time that updates the day to the current day.
     * @param description Represents the description of the task.
     * @param time Represents the time of the task.
     */
    public RecurringTask(String description, String time) {
        super(description);
        this.time = time.trim();
    }

    /**
     * Returns the string representation of RecurringTask.
     * @return Returns the string representation of Recurring task.
     */
    @Override
    public String toString() {
        LocalDate curDate = LocalDate.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime desiredTime = LocalTime.parse(this.time, timeFormatter);
        LocalDateTime modDate = curDate.atTime(desiredTime);
        return "[R]" + super.toString() + "(Due :" + modDate.toString() + ")";
    }
}
