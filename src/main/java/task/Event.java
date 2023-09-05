package task;

import java.time.LocalDateTime;

import duke.Duke;

/**
 * This class represents the task type Event.
 */
class Event extends Task {
    private LocalDateTime startDayDateTime;
    private LocalDateTime endDayDateTime;

    /**
     * Creates an Event object.
     *
     * @param taskDescription The name of the task.
     * @param startDayDateTime The date and time of the start of the event.
     * @param endDayDateTime The date and time of the end of the event.
     * @return Returns a Task object.
     */
    Event(String taskDescription, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        super(taskDescription);

        this.endDayDateTime = endDayDateTime;
        this.startDayDateTime = startDayDateTime;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDayDateTime.format(Duke.FORMAT)
                + " to: " + endDayDateTime.format(Duke.FORMAT) + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String write() {
        return "event " + super.getTaskDescription() + "/from " + this.startDayDateTime.format(Duke.FORMAT)
                + " /to " + this.endDayDateTime.format(Duke.FORMAT) + "\n";
    }
}
