package qi.task;

/**
 * Represents the task categorized as event
 * by users.
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

    /**
     * Takes in the event description as well as
     * the period during which it lasts.
     *
     * @param task String description of the event.
     * @param startTime String representation of the starting time.
     * @param endTime String representation of the ending time.
     */
    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String checkBox = this.isDone ? "[E][X] " : "[E][ ] ";
        String description = String.format("%s (from: %s to: %s)", this.task, this.startTime, this.endTime);
        return checkBox + description;
    }
}

