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
    public String status() {
        return isDone() ? "[E][X]" : "[E][ ]";
    }

    @Override
    public String taskName() {
        return super.taskName() + " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String type() {
        return "Task.Events";
    }

    /**
     * Returns starting date of event
     *
     * @return start date
     */
    public LocalDate startDate() {
        return LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
    }

    /**
     * Returns ending date of event
     *
     * @return end date
     */
    public LocalDate endDate() {
        return LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Events) {
            Events temp = (Events) o;
            return this.startDate().equals(temp.startDate()) && this.endDate().equals(temp.endDate())
                    && taskName().equals(temp.taskName()) && isDone() == temp.isDone();
        }
        return false;
    }
}
