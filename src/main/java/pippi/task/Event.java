package pippi.task;

public class Event extends Task {
    private String start;
    private String end;
    public Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the String representation of an event task to the UI
     * @return Event string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + "to: " + this.end + ")";
    }

    /**
     * Returns the String representation of an event task written to the memory
     * @return Event string representation
     */
    @Override
    public String toMemory() { return "E " + super.getStatus() + super.getDescription() + " | " + start + "to " + end; }
}
