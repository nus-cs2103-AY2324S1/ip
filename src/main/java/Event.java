public class Event extends Task {

    private final String start;
    private final String end;

    public Event(String input, String name, String start, String end) {
        super(input, name);
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
        return String.format("[%s][E] %s (from: %s to: %s)", super.checkDone(), super.getName(), start, end);
    }
}