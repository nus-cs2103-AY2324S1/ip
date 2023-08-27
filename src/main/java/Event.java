/**
 * Event task.
 */
public class Event extends Task{
    private String from;
    private String to;
    public Event(String taskContent, String from, String to) {
        super(taskContent);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String statusAndTaskContent = super.toString();
        return String.format("  [E] %s (from: %s to: %s)", statusAndTaskContent, from, to);
    }

    public static Event create(String status, String description, String from, String to) {
        Event task = new Event(description, from, to);
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("E | %s | from %s to %s\n", super.saveToFileLine(), from, to);
    }
}
