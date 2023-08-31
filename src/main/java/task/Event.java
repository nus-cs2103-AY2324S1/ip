package task;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, String start, String end, Boolean marked) {
        super(name, marked);
        this.start = start;
        this.end = end;
    }

    @Override
    public Event mark() {
        return new Event(this.name, this.start, this.end, true);
    }

    @Override
    public Event unmark() {
        return new Event(this.name, this.start, this.end, false);
    }

    /**
     * Returns the name of task with start and end times.
     *
     * @return Task.Task to be done
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                this.start,
                this.end
        );
    }
}
