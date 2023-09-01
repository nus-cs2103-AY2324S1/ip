package chatterchicken.task;

import java.time.LocalDate;

public class Event extends Task {

    private final LocalDate start;
    private final LocalDate end;

    public Event(String taskDescription, String name, LocalDate start, LocalDate end) {
        super(taskDescription, name);
        this.start = start;
        this.end = end;
    }

    /**
     * Generates the formatted representation of the event task.
     * The format includes the task status, task type, description, start time, and end time.
     *
     * @return The formatted representation of the event task.
     */
    @Override
    public String getTask() {
        return String.format("[%s][E] %s (from: %s to: %s)",
                super.checkDone(),
                super.getName(),
                super.formatDate(start),
                super.formatDate(end));
    }

    @Override
    public String getTaskDescription() {
        return "event " + super.getTaskDescription();
    }
}