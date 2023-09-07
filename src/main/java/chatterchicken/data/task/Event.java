package chatterchicken.data.task;

import java.time.LocalDate;

/**
 * The Event class represents an event task in the task management application.
 * It extends the parent Task class and includes information about the event's start and end dates.
 * Event tasks are used to schedule events with specific start and end times.
 */
public class Event extends Task {

    private final LocalDate start; //the start date of the event
    private final LocalDate end; //the end date of the event

    /**
     * Constructs an Event object with the provided task description, event name, start date, and end date.
     *
     * @param taskDescription The description of the event task.
     * @param name The name or title of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String taskDescription, String name, LocalDate start, LocalDate end) {
        super(taskDescription, name);
        this.start = start;
        this.end = end;
    }

    /**
     * Generates the formatted representation of the event task for display purposes.
     * The format includes the task status, task type, description, start date, and end date.
     *
     * @return The formatted representation of the event task.
     */
    @Override
    public String getTaskForPrinting() {
        return String.format("[%s][E] %s (from: %s to: %s)",
                super.checkDone(),
                super.getName(),
                super.formatDate(start),
                super.formatDate(end));
    }

    /**
     * Gets the input representation of the event task.
     * The input format is suitable for creating and parsing event tasks.
     *
     * @return The input representation of the event task.
     */
    public String getInput() {
        return "event " + super.getTaskDescription();
    }
}