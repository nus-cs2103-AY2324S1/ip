package pogo.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusMessage() {
        return "[E]" + super.getStatusMessage() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFormattedString() {
        return String.format("E | %s | %s | %s", super.toFormattedString(), this.from, this.to);
    }

    public static Event fromFormattedString(String input) throws PogoInvalidTaskException {
        String[] split = input.split(" \\| ");
        if (split.length != 5) {
            throw new PogoInvalidTaskException();
        }

        if (!split[0].equals("E")) {
            throw new PogoInvalidTaskException();
        }

        if (!split[1].equals("1") && !split[1].equals("0")) {
            throw new PogoInvalidTaskException();
        }

        boolean isDone = split[1].equals("1");

        Event event = new Event(split[2], split[3], split[4]);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }
}
