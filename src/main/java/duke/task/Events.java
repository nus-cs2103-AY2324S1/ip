package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task with defined start and end date
 *
 * @author Lian Zhi Xuan
 */
public class Events extends Task {

    private String startDate;

    private String endDate;

    public Events(String task, LocalDate start, LocalDate end) {
        super(task);
        startDate = start.toString();
        endDate = end.toString();
    }

    @Override
    public String getStatus() {
        return isDone() ? "[E][X]" : "[E][ ]";
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String getType() {
        return "Task.Events";
    }

    /**
     * Returns starting date of event
     *
     * @return start date
     */
    public LocalDate getStartDate() {
        return LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
    }

    /**
     * Returns ending date of event
     *
     * @return end date
     */
    public LocalDate getEndDate() {
        return LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Events) {
            Events temp = (Events) o;
            return this.getStartDate().equals(temp.getStartDate()) && this.getEndDate().equals(temp.getEndDate())
                    && getTaskName().equals(temp.getTaskName()) && isDone() == temp.isDone();
        }
        return false;
    }
}
