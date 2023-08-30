package Task;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + " | " + end;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (from: " + start + " to: " + end + ")";
    }
}