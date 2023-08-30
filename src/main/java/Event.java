public class Event extends Task {
    protected String eventStart;
    protected String eventEnd;

    public Event(String description, String eventStart, String eventEnd, boolean isDone) {
        super(description, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String saveToFileString(){
        return "E " + (super.isDone ? "| 1 | " : " | 0 | ") + super.toString() + " | " + eventStart
                + " | " + eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon()  + super.toString() + " (from: " + eventStart
                + " to:" + eventEnd + ")";
    }
}
