package duke.task;

import java.time.LocalDate;

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
        return super.taskName() + "(from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String type() {
        return "Task.Events";
    }
}
