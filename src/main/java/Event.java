public class Event extends Task{

    protected String start;
    protected String end;

    private Event(String title, String start, String end) {
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
    public static Task addEvent(String title, String start, String end) {
        Task event = new Event(title, start, end);
        taskList.add(event);
        return event;
    }

    @Override
    public String toString() {
        String time = String.format(" (from: %s to: %s)", start, end);
        return "[E]" + super.toString() + time;
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + String.format(" | %s | %s", start, end);
    }
}
