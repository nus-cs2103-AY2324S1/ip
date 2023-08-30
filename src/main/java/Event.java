public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0].trim();
        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();
        String from = parts[4].trim();
        String to = parts[5].trim();

        Event event = new Event(taskName, from, to);

        if (isCompleted.equals("1")) {
            event.setCompleted();
        }

        return event;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
