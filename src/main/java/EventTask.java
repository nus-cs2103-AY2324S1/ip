public class EventTask extends Task {

    private final String from;
    private final String to;

    /**
     * Constructor for Task.
     *
     * @param description of the task.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String getFileDescriptor() {
        return super.getFileDescriptor() + String.format("| %s | %s | %s", this.getFrom(), this.getTo(), "EVENT");
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
