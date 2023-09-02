package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public LocalDate startDate() {
        return LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
    }

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
