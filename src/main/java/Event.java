import java.time.LocalDateTime;

public class Event extends Task{

    protected LocalDateTime start;
    protected LocalDateTime end;

    private Event(String title, LocalDateTime start, LocalDateTime end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    /**
     * Adds a new Event task to the list of tasks.
     * @param title Title of task.
     * @param start Start time of task.
     * @param end End time of task.
     * @return Task object created.
     */
    public static Task addEvent(String title, LocalDateTime start, LocalDateTime end) {
        Task event = new Event(title, start, end);
        taskList.add(event);
        return event;
    }

    @Override
    public String toString() {
        String from = start.format(displayFormat);
        String to = end.format(displayFormat);
        String time = String.format(" (from: %s to: %s)", from, to);
        return "[E]" + super.toString() + time;
    }

    /**
     * Convert Event task to a string for storing in data file.
     * @return Formatted string with data for Event task.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + String.format(" | %s | %s", start.format(dataFormat), end.format(dataFormat));
    }
}
